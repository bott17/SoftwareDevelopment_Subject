package ds.practica2.juegopreguntas.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by bott1 on 14/04/2015.
 */
public abstract class MyActionBarActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract void initComponents();
}
