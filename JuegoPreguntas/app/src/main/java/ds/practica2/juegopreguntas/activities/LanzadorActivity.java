package ds.practica2.juegopreguntas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ds.practica2.juegopreguntas.juego.TipoJuego;
import ds.practica2.juegopreguntas.manejadores.GameManager;
import ds.practica2.juegopreguntas.preguntas.Pregunta;
import ds.practica2.juegopreguntas.preguntas.PreguntaTexto;
import ds.practica2.juegopreguntas.preguntas.TipoPregunta;
import ds.practica2.juegopreguntas.R;

public class LanzadorActivity extends MyActionBarActivity {

    private static String TAG = "LanzadorActivity";

    // Elementos visuales
    private Button botonPrincipal, botonPregunta, botonResponder;
    TextView textTituloPregunta;

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

        gameManager = GameManager.getInstance(TipoJuego.DEFAULT);

        textTituloPregunta = (TextView) findViewById(R.id.textTituloPregunta);

        botonPrincipal = (Button) findViewById(R.id.bottonPrincipal);
        botonPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        botonPregunta = (Button) findViewById(R.id.botonPregunta);
        botonPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarPregunta();
            }
        });


        botonResponder = (Button) findViewById(R.id.botoContestar);
        botonResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Respuestas falseadas
                ArrayList<Integer> respuestasFalsas = new ArrayList<Integer>();
                respuestasFalsas.add(0);
                respuestasFalsas.add(1);

                // Valoracion de las respuestas, el primer campo de pair indica el resultado
                if(gameManager.validarPregunta(preguntaActual, respuestasFalsas).first){
                    Toast.makeText(getApplicationContext(), "¡Respuesta Correcta!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "¡Respuesta Incorrecta!", Toast.LENGTH_SHORT).show();

                cambiarPregunta();
            }
        });
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

        gameManager = GameManager.getInstance(TipoJuego.DEFAULT);
        preguntaActual = gameManager.getUltimaPregunta();

        obtenerDatosPregunta(preguntaActual);
    }


    private void obtenerDatosPregunta(Pregunta pregunta){

        if (preguntaActual.getTipo().equals(TipoPregunta.DEFAULT)) {
            textTituloPregunta.setText(preguntaActual.getTituloPregunta());
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
