package ds.practica2.juegopreguntas.manejadores;

import android.util.Log;

import java.util.ArrayList;

import ds.practica2.juegopreguntas.preguntas.CategoriaPregunta;
import ds.practica2.juegopreguntas.preguntas.Pregunta;
import ds.practica2.juegopreguntas.preguntas.PreguntaFactoria;
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

        ArrayList<String> respuestas = new ArrayList<>();
        respuestas.add("respuesta1");
        respuestas.add("respuesta2");
        respuestas.add("respuesta3");
        ArrayList<Integer> soluciones = new ArrayList<>();
        soluciones.add(0);
        soluciones.add(1);

        preguntas.add(PreguntaFactoria.makePregunta("Pregunta factoria1", CategoriaPregunta.CIENCIAS, respuestas, soluciones ));

        respuestas = new ArrayList<>();
        respuestas.add("respuesta1");
        respuestas.add("respuesta2");
        respuestas.add("respuesta3");
        soluciones = new ArrayList<>();
        soluciones.add(2);
        soluciones.add(1);

        preguntas.add(PreguntaFactoria.makePregunta("Pregunta factoria2", CategoriaPregunta.CIENCIAS, respuestas, soluciones ));

        return preguntas;
    }
}
