package ds.practica2.juegopreguntas.activities;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import ds.practica2.juegopreguntas.R;
import ds.practica2.juegopreguntas.tipos.TipoCategoria;
import ds.practica2.juegopreguntas.tipos.TipoJuego;
import ds.practica2.juegopreguntas.manejadores.GameManager;
import ds.practica2.juegopreguntas.manejadores.SonidoManager;
import ds.practica2.juegopreguntas.popups.PopupFallo;
import ds.practica2.juegopreguntas.popups.PopupFinJuegoResumen;
import ds.practica2.juegopreguntas.preguntas.Pregunta;
import ds.practica2.juegopreguntas.preguntas.PreguntaSonido;
import ds.practica2.juegopreguntas.tipos.TipoPregunta;
import ds.practica2.juegopreguntas.tipos.TipoRespuestas;

import static ds.practica2.juegopreguntas.tipos.TipoPregunta.DEFAULT;


public class LanzadorActivity extends MyActionBarActivity {

    private static final String TAG = "LanzadorActivity";

    // Elementos del reproductor
    private Handler durationHandler = new Handler();
    private double timeElapsed = 0, finalTime = 0;

    // Elementos visuales
    private static LinearLayout marco;
    private Button botonPrincipal;
    private Button botonRespuesta1, botonRespuesta2, botonRespuesta3, botonRespuesta4;
    private TextView textTituloPregunta, textDificultad;
    private ToggleButton toggleButtonPlay;
    private ImageView botonParar;
    private SeekBar seekBar;
    private ImageView foto1, foto2, foto3, foto4;
    private TextView textoCategoria;

    private PopupFallo popupFallo;
    //private PopupFinJuegoResumen resumen;


    View.OnClickListener seleccionarRespuestaListener;

    // Referencias
    private static final int respuesta1 = 1;
    private static final int respuesta2 = 2;
    private static final int respuesta3 = 3;
    private static final int respuesta4 = 4;

    private GameManager gameManager;
    private Pregunta preguntaActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanzador);

        Log.d(TAG, "Creando actividad de juego...");

        initComponents();

        if(gameManager.checkNuevoJuego()) {
            Log.d(TAG, "No hay juego iniciado.... Creando nuevo juego");
            initGame();
        }
        else{
            Log.d(TAG, "Cargando juego iniciado...");
            resumeGame();
        }
    }

    private void initGame() {;

        cambiarPregunta();
    }

    @Override
    protected void initComponents() {

        // Creando el popup de de fallo
        popupFallo = new PopupFallo(this, null);
        View.OnClickListener continueGameListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupFallo.cerrarPopup();
                cambiarPregunta();
            }
        };
        View.OnClickListener finalizarJuegoListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupFallo.cerrarPopup();

                rendirse();
            }
        };
        popupFallo.setContinueListener(continueGameListener);
        popupFallo.setFinalizarListener(finalizarJuegoListener);

        // Cargando los sonidos
        SonidoManager.load(getApplicationContext());

        textoCategoria = (TextView)findViewById(R.id.textViewCategoria);

        gameManager = GameManager.getInstance(getApplicationContext(), TipoJuego.DEFAULT);

        textTituloPregunta = (TextView) findViewById(R.id.textTituloPregunta);
        textDificultad = (TextView)findViewById(R.id.textViewDificultad);

        botonPrincipal = (Button) findViewById(R.id.bottonPrincipal);
        botonPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        marco = (LinearLayout)findViewById(R.id.layoutMarco);

        // Listener de los botones de respuestas
        seleccionarRespuestaListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Para el sonido al cambiar de pregunta
                if(SonidoManager.reproduciendo()) {
                    Log.d(TAG, "parando sonido");
                    SonidoManager.pararSonido();
                }

                ArrayList<Integer> respuestasSeleccionadas = new ArrayList<>();

                // En caso de multirespuesta
                if(preguntaActual.getTipoRespuestas() == TipoRespuestas.MULTIPLE){
                    // TODO Implentar. Sin implementar en esta version
                }
                // En caso de respuesta unica
                else if(preguntaActual.getTipoRespuestas() == TipoRespuestas.SIMPLE){

                    int index = -1;
                    switch ((int)v.getTag(v.getId())){
                        case 1:
                            index = 0;
                            break;
                        case 2:
                            index = 1;
                            break;
                        case 3:
                            index = 2;
                            break;
                        case 4:
                            index = 3;
                            break;
                    }
                    respuestasSeleccionadas.add(index);

                    if(gameManager.validarPregunta(preguntaActual, respuestasSeleccionadas).first) {
                        Toast.makeText(getApplicationContext(), "¡Respuesta Correcta!", Toast.LENGTH_SHORT).show();
                        sonidoResultado(true);
                        cambiarPregunta();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "¡Respuesta Incorrecta!", Toast.LENGTH_SHORT).show();
                        sonidoResultado(false);

                        // El popup lanza el cambio de pregunta si es necesario
                        lanzarPopupFallo();
                    }
                }


            }
        };




    }

    private void lanzarPopupFallo() {

        popupFallo.showChargedPopup(new Point(0, 0));

    }

    private void sonidoResultado(boolean acierto) {

        SonidoManager.reproducirResultado(acierto);
    }

    private void cambiarPregunta(){

        preguntaActual = gameManager.siguientePregunta();

        // Comprobación de que queden preguntas
        if(preguntaActual != null)
            obtenerDatosPregunta(preguntaActual);
        else {
            showPopupFinal(gameManager.getPreguntasFallidas());
        }

    }

    private void finalizarJuego() {

        gameManager.finJuego();

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    private void showPopupFinal(ArrayList<Pregunta> preguntasFallidas){

        // Obtener titulo y respuesta correcta para todas las preguntas
        ArrayList<Pair<String, String> > soluciones = new ArrayList<>();

        for(Pregunta preguntaTemporal: preguntasFallidas) {
            String respuestaCorrecta = preguntaTemporal.getRespuestaCorrecta();
            Pair<String, String> solucion = new Pair<>(preguntaTemporal.getTituloPregunta(), respuestaCorrecta);
            Log.d(TAG, solucion.first + " " + solucion.second);

            soluciones.add(solucion);
        }


        // Lanzar el popup
        final PopupFinJuegoResumen resumen = new PopupFinJuegoResumen(LanzadorActivity.this, null, soluciones);
        resumen.showChargedPopup(new Point(0,0));
        View.OnClickListener listenerPrincipal = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Cerrando respuestas...");
                resumen.cerrarPopup();
                finalizarJuego();
            }
        };
        resumen.setPrincipalListener(listenerPrincipal);
    }

    private void rendirse(){
        /*
        // Lanzar popup de soluciones
        String respuestaCorrecta = preguntaActual.getRespuestaCorrecta();
        Pair<String, String> solucion = new Pair<>(preguntaActual.getTituloPregunta(), respuestaCorrecta);
        Log.d(TAG, solucion.first + " " + solucion.second);
        ArrayList<Pair<String, String> > soluciones = new ArrayList<>();
        soluciones.add(solucion);
        */

        ArrayList<Pregunta> preguntaFallida = new ArrayList<>();
        preguntaFallida.add(preguntaActual);
        showPopupFinal(preguntaFallida);
/*
        resumen = new PopupFinJuegoResumen(LanzadorActivity.this, null, soluciones);
        resumen.showChargedPopup(new Point(0,0));
        View.OnClickListener listenerPrincipal = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Cerrando respuestas...");
                resumen.cerrarPopup();
                finalizarJuego();
            }
        };
        resumen.setPrincipalListener(listenerPrincipal);
        */
    }

    private void resumeGame() {

        gameManager = GameManager.getInstance(getApplicationContext(), TipoJuego.DEFAULT);
        preguntaActual = gameManager.getUltimaPregunta();

        obtenerDatosPregunta(preguntaActual);
    }


    private void obtenerDatosPregunta(Pregunta pregunta){

        marco.removeAllViewsInLayout();

        textTituloPregunta.setText(preguntaActual.getTituloPregunta());
        textDificultad.setText(Integer.toString(preguntaActual.getDificultad()));

        textoCategoria.setText(TipoCategoria.toString(pregunta.getCategoria()));

        View viewPreguntas;

        if (preguntaActual.getTipo().equals(DEFAULT)) {

            viewPreguntas = LayoutInflater.from(getApplicationContext()).inflate(R.layout.pregunta_estandar, null, false);
            marco.addView(viewPreguntas);

            cargarElementosPregunta(preguntaActual.getTipo());

        }
        else if (preguntaActual.getTipo().equals(TipoPregunta.SONIDO)) {

            viewPreguntas = LayoutInflater.from(getApplicationContext()).inflate(R.layout.pregunta_sonido, null, false);
            marco.addView(viewPreguntas);

            SonidoManager.cargarSonido(((PreguntaSonido) pregunta).getRefMultimedia());

            cargarElementosPregunta(preguntaActual.getTipo());

        }
        else if(preguntaActual.getTipo().equals(TipoPregunta.IMAGEN)){

            viewPreguntas = LayoutInflater.from(getApplicationContext()).inflate(R.layout.pregunta_imagenes, null, false);
            marco.addView(viewPreguntas);

            cargarElementosPregunta(preguntaActual.getTipo());


        }

    }

    private void cargarElementosPregunta(TipoPregunta tipo) {

        switch (tipo){
            case SONIDO:
                toggleButtonPlay = (ToggleButton) findViewById(R.id.botonTogglePlay);
                toggleButtonPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (toggleButtonPlay.isChecked()) { // Checked - Pause icon visible
                            SonidoManager.reproducirSonido();
                            timeElapsed = SonidoManager.getCurrentPosition();
                            seekBar.setProgress((int) timeElapsed);
                            durationHandler.postDelayed(updateSeekBarTime, 100);
                        } else { // Unchecked - Play icon visible
                            SonidoManager.pausarSonido();
                        }
                    }
                });

                botonParar = (ImageView) findViewById(R.id.botonImagenStop);
                botonParar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SonidoManager.pararSonido();
                        toggleButtonPlay.setChecked(false);
                    }
                });

                seekBar = (SeekBar) findViewById(R.id.seekBar);
                seekBar.setMax(SonidoManager.getDuration());
                seekBar.setClickable(false);
                // No tiene break ya que carga tambien los elementos de Default

            case DEFAULT:
                // Aniandiendo tags identificativos
                botonRespuesta1 = (Button) findViewById(R.id.botonPregunta1);
                botonRespuesta1.setTag(R.id.botonPregunta1, respuesta1);
                botonRespuesta2 = (Button) findViewById(R.id.botonpregunta2);
                botonRespuesta2.setTag(R.id.botonpregunta2, respuesta2);
                botonRespuesta3 = (Button) findViewById(R.id.botonPregunta3);
                botonRespuesta3.setTag(R.id.botonPregunta3, respuesta3);
                botonRespuesta4 = (Button) findViewById(R.id.botonPregunta4);
                botonRespuesta4.setTag(R.id.botonPregunta4, respuesta4);

                botonRespuesta1.setOnClickListener(seleccionarRespuestaListener);
                botonRespuesta2.setOnClickListener(seleccionarRespuestaListener);
                botonRespuesta3.setOnClickListener(seleccionarRespuestaListener);
                botonRespuesta4.setOnClickListener(seleccionarRespuestaListener);


                botonRespuesta1.setText(preguntaActual.getRespuestas().get(0).first);
                botonRespuesta2.setText(preguntaActual.getRespuestas().get(1).first);
                botonRespuesta3.setText(preguntaActual.getRespuestas().get(2).first);
                botonRespuesta4.setText(preguntaActual.getRespuestas().get(3).first);
                break;

            case IMAGEN:
                foto1 = (ImageView) findViewById(R.id.imageViewFoto1);
                foto1.setTag(R.id.imageViewFoto1,respuesta1);
                foto2 = (ImageView) findViewById(R.id.imageViewFoto2);
                foto2.setTag(R.id.imageViewFoto2,respuesta2);
                foto3 = (ImageView) findViewById(R.id.imageViewFoto3);
                foto3.setTag(R.id.imageViewFoto3,respuesta3);
                foto4 = (ImageView) findViewById(R.id.imageViewFoto4);
                foto4.setTag(R.id.imageViewFoto4,respuesta4);

                foto1.setOnClickListener(seleccionarRespuestaListener);
                foto2.setOnClickListener(seleccionarRespuestaListener);
                foto3.setOnClickListener(seleccionarRespuestaListener);
                foto4.setOnClickListener(seleccionarRespuestaListener);

                int idResource = getApplicationContext().getResources().getIdentifier(preguntaActual.getRespuestas().get(0).first, "drawable", getApplicationContext().getPackageName());
                foto1.setImageDrawable(getResources().getDrawable(idResource));
                idResource = getApplicationContext().getResources().getIdentifier(preguntaActual.getRespuestas().get(1).first, "drawable", getApplicationContext().getPackageName());
                foto2.setImageDrawable(getResources().getDrawable(idResource));
                idResource = getApplicationContext().getResources().getIdentifier(preguntaActual.getRespuestas().get(2).first, "drawable", getApplicationContext().getPackageName());
                foto3.setImageDrawable(getResources().getDrawable(idResource));
                idResource = getApplicationContext().getResources().getIdentifier(preguntaActual.getRespuestas().get(3).first, "drawable", getApplicationContext().getPackageName());
                foto4.setImageDrawable(getResources().getDrawable(idResource));
                break;
        }
    }

    //handler to change seekBarTime
    private Runnable updateSeekBarTime = new Runnable() {
        public void run() {
            //get current position
            double timeElapsed = SonidoManager.getCurrentPosition();
            //set seekbar progress
            seekBar.setProgress((int) timeElapsed);

            //repeat yourself that again in 100 miliseconds
            durationHandler.postDelayed(this, 100);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lanzador, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(SonidoManager.reproduciendo())
            SonidoManager.pararSonido();
    }
}
