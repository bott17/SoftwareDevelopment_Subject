package ds.practica2.juegopreguntas.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import ds.practica2.juegopreguntas.R;


public class MainActivity extends MyActionBarActivity {

    private static String TAG = "MainActivity";

    private Button botonJuego, botonEstadisticas, botonExterno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    @Override
    protected void initComponents() {

        Log.d(TAG, "Init components");

        botonEstadisticas = (Button) findViewById(R.id.bottonEstad);
        botonEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EstadisticasActivity.class);
                startActivity(i);
            }
        });

        botonJuego = (Button) findViewById(R.id.bottonJuego);
        botonJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LanzadorActivity.class);
                startActivity(i);
            }
        });

        botonExterno = (Button) findViewById(R.id.bottonExter);
        // TODO Aniadir

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
