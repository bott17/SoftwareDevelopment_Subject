package ds.practica2.juegopreguntas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ds.practica2.juegopreguntas.manejadores.SonidoManager;
import ds.practica2.juegopreguntas.juego.TipoJuego;
import ds.practica2.juegopreguntas.manejadores.GameManager;
import ds.practica2.juegopreguntas.preguntas.Pregunta;
import ds.practica2.juegopreguntas.preguntas.TipoPregunta;
import ds.practica2.juegopreguntas.R;
import ds.practica2.juegopreguntas.tipos.TipoRespuestas;

public class LanzadorActivity extends MyActionBarActivity {

    private static final String TAG = "LanzadorActivity";

    // Elementos visuales
    private static LinearLayout marco;
    private Button botonPrincipal;
    private Button botonRespuesta1, botonRespuesta2, botonRespuesta3, botonRespuesta4;
    private TextView textTituloPregunta, textDificultad;

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

        // Cargando los sonidos
        SonidoManager.load(getApplicationContext());

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

                ArrayList<Integer> respuestasSeleccionadas = new ArrayList<>();

                // En caso de multirespuesta
                if(preguntaActual.getTipoRespuestas() == TipoRespuestas.MULTIPLE){
                    // TODO Implentar. Se podria hacer en otro listener diferente
                }
                // En caso de respuesta unica
                else if(preguntaActual.getTipoRespuestas() == TipoRespuestas.SIMPLE){

                    int index = -1;
                    switch ((int)v.getTag(R.id.botonPregunta1)){
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
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "¡Respuesta Incorrecta!", Toast.LENGTH_SHORT).show();
                        sonidoResultado(false);
                    }
                }

                cambiarPregunta();
            }
        };




    }

    private void sonidoResultado(boolean acierto) {

        SonidoManager.reproducirResultado(acierto);
    }

    private void cambiarPregunta(){

        preguntaActual = gameManager.siguientePregunta();

        // Comprobación de que queden preguntas
        if(preguntaActual != null)
            obtenerDatosPregunta(preguntaActual);
        else
            finalizarJuego();

    }

    private void finalizarJuego() {
        gameManager.finJuego();

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
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

        View viewPreguntas;

        if(preguntaActual.getTipo().equals(TipoPregunta.DEFAULT) || preguntaActual.getTipo().equals(TipoPregunta.SONIDO)) {

            if (preguntaActual.getTipo().equals(TipoPregunta.DEFAULT)) {

                viewPreguntas = LayoutInflater.from(getApplicationContext()).inflate(R.layout.pregunta_estandar, null, false);


            } else{

                viewPreguntas = LayoutInflater.from(getApplicationContext()).inflate(R.layout.pregunta_sonido, null, false);


            }

            marco.addView(viewPreguntas);

            // Aniandiendo tags identificativos
            botonRespuesta1 = (Button) findViewById(R.id.botonPregunta1);
            botonRespuesta1.setTag(R.id.botonPregunta1, respuesta1);
            botonRespuesta2 = (Button) findViewById(R.id.botonpregunta2);
            botonRespuesta2.setTag(R.id.botonPregunta1, respuesta2);
            botonRespuesta3 = (Button) findViewById(R.id.botonPregunta3);
            botonRespuesta3.setTag(R.id.botonPregunta1, respuesta3);
            botonRespuesta4 = (Button) findViewById(R.id.botonPregunta4);
            botonRespuesta4.setTag(R.id.botonPregunta1, respuesta4);

            botonRespuesta1.setOnClickListener(seleccionarRespuestaListener);
            botonRespuesta2.setOnClickListener(seleccionarRespuestaListener);
            botonRespuesta3.setOnClickListener(seleccionarRespuestaListener);
            botonRespuesta4.setOnClickListener(seleccionarRespuestaListener);


            botonRespuesta1.setText(preguntaActual.getRespuestas().get(0).first);
            botonRespuesta2.setText(preguntaActual.getRespuestas().get(1).first);
            botonRespuesta3.setText(preguntaActual.getRespuestas().get(2).first);
            botonRespuesta4.setText(preguntaActual.getRespuestas().get(3).first);
        }
    }


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
}
