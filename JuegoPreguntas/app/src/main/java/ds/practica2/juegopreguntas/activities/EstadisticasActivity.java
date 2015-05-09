package ds.practica2.juegopreguntas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ds.practica2.juegopreguntas.R;
import ds.practica2.juegopreguntas.manejadores.EstadisticasManager;

public class EstadisticasActivity extends MyActionBarActivity {

    private Button botonPrincipal;
    private TextView textNumPartidas, textNumAciertos, textNumFallos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        initComponents();
        recuperarResumenEstadisticas();
    }

    private void recuperarResumenEstadisticas() {

        ArrayList<Integer> resumenEstadisticas = EstadisticasManager.getResumen();

        // Control de que hara partidas realizadas
        if(resumenEstadisticas.size() > 0) {
            textNumPartidas.setText(Integer.toString(resumenEstadisticas.get(0)));
            textNumAciertos.setText(Integer.toString(resumenEstadisticas.get(1)));
            textNumFallos.setText(Integer.toString(resumenEstadisticas.get(2)));
        }

    }

    @Override
    protected void initComponents() {

        botonPrincipal = (Button) findViewById(R.id.bottonPrincipal);
        botonPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        textNumPartidas = (TextView) findViewById(R.id.textViewTotalPartidas);
        textNumAciertos = (TextView) findViewById(R.id.textViewTotalAciertos);
        textNumFallos = (TextView) findViewById(R.id.textViewTotalFallos);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_estadisticas, menu);
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
