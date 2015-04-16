package ds.practica2.juegopreguntas.manejadores;

import android.util.Log;

import java.util.ArrayList;

import ds.practica2.juegopreguntas.preguntas.Pregunta;
import ds.practica2.juegopreguntas.preguntas.PreguntaTexto;
import ds.practica2.juegopreguntas.preguntas.TipoPregunta;

/**
 * Created by bott1 on 14/04/2015.
 */
public abstract class InfoManager {

    private static String TAG = "InfoManager";

    public static ArrayList<Pregunta> getPreguntas(int numeroPreguntas, ArrayList<TipoPregunta> tiposDePreguntas) {

        Log.d(TAG, "Obteniendo preguntas...");

        // TODO Recuperar imagenes almacenadas

        ArrayList<Pregunta> preguntas = new ArrayList<>();

        preguntas.add(new PreguntaTexto("Pregunta1"));
        preguntas.add(new PreguntaTexto("Pregunta2"));

        return preguntas;
    }
}
