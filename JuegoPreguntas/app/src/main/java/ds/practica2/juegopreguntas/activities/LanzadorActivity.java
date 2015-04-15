package ds.practica2.juegopreguntas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ds.practica2.juegopreguntas.Juego.TipoJuego;
import ds.practica2.juegopreguntas.Manejadores.GameManager;
import ds.practica2.juegopreguntas.Preguntas.Pregunta;
import ds.practica2.juegopreguntas.Preguntas.PreguntaTexto;
import ds.practica2.juegopreguntas.Preguntas.TipoPregunta;
import ds.practica2.juegopreguntas.R;

public class LanzadorActivity extends MyActionBarActivity {

    private static String TAG = "LanzadorActivity";

    // Elementos visuales
    private Button botonPrincipal, botonPregunta, botonResponder;
    TextView textTituloPregunta;

    private GameManager gameManager = null;
    private Pregunta preguntaActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanzador);

        Log.d(TAG, "Creando actividad de juego...");

        // TODO siempre se lanza la actividad volviendo del menu principal. Puede dar null pointer en la ultima pregunta
        initComponents();
        initGame();
    }

    private void initGame() {
        gameManager = GameManager.getInstance(TipoJuego.DEFAULT);
        cambiarPregunta();
    }

    @Override
    protected void initComponents() {

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
    }

    private void cambiarPregunta(){

        preguntaActual = gameManager.siguientePregunta();

        if (preguntaActual.getTipo().equals(TipoPregunta.TEXTO)) {
            textTituloPregunta.setText(((PreguntaTexto)preguntaActual).getTituloPregunta());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        gameManager.finJuego();
        preguntaActual = null;
    }
}
