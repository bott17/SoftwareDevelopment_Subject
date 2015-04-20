package ds.practica2.juegopreguntas.manejadores;

import java.util.ArrayList;

import ds.practica2.juegopreguntas.preguntas.Pregunta;

/**
 * Created by bott1 on 15/04/2015.
 */
public abstract class EstadisticasManager {

    public static void updateAcierto(Pregunta pregunta) {

        InfoManager.updateAcierto(pregunta.getTipo(), pregunta.getCategoria());
    }

    public static void updateFallo(Pregunta pregunta, ArrayList<Integer> respuestas) {

        InfoManager.updateFallo(pregunta.getTipo(), pregunta.getCategoria());
    }
}
