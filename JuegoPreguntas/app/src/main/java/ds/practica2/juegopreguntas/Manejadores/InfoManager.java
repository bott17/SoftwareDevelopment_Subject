package ds.practica2.juegopreguntas.Manejadores;

import java.util.ArrayList;

import ds.practica2.juegopreguntas.Preguntas.Pregunta;
import ds.practica2.juegopreguntas.Preguntas.PreguntaTexto;
import ds.practica2.juegopreguntas.Preguntas.TipoPregunta;

/**
 * Created by bott1 on 14/04/2015.
 */
public abstract class InfoManager {

    public static ArrayList<Pregunta> getPreguntas(int numeroPreguntas, ArrayList<TipoPregunta> tiposDePreguntas) {

        // TODO Recuperar imagenes almacenadas

        ArrayList<Pregunta> preguntas = new ArrayList<>();

        preguntas.add(new PreguntaTexto("Pregunta1"));
        preguntas.add(new PreguntaTexto("Pregunta2"));

        return preguntas;
    }
}
