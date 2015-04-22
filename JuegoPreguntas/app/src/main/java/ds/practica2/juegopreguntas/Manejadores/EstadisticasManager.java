package ds.practica2.juegopreguntas.manejadores;

import java.util.ArrayList;

import ds.practica2.juegopreguntas.preguntas.Pregunta;

/**
 * Created by bott1 on 15/04/2015.
 */
public abstract class EstadisticasManager {

    public static void updateAcierto(int idPartida, Pregunta pregunta) {

        InfoManager.updateAcierto(idPartida, pregunta.getTipo(), pregunta.getCategoria());
    }

    public static void updateFallo(int idPartida, Pregunta pregunta, ArrayList<Integer> respuestas) {

        InfoManager.updateFallo(idPartida, pregunta.getTipo(), pregunta.getCategoria());
    }
}
