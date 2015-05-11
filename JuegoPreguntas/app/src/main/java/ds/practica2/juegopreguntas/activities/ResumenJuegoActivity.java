package ds.practica2.juegopreguntas.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ds.practica2.juegopreguntas.R;
import ds.practica2.juegopreguntas.manejadores.GameManager;

public class ResumenJuegoActivity extends MyActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_juego);

        recuperarResumenJuego();

        GameManager.getInstance(null, null).finJuego();
    }

    private void recuperarResumenJuego() {
        // TODO implementar
        //GameManager.getInstance(getApplicationContext(),null).resumenJuego();
    }

    @Override
    protected void initComponents() {
        // TODO implementar
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resumen_juego, menu);
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
