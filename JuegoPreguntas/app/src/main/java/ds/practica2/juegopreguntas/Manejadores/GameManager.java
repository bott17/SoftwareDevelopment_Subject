package ds.practica2.juegopreguntas.Manejadores;

import ds.practica2.juegopreguntas.Preguntas.Pregunta;
import ds.practica2.juegopreguntas.Preguntas.PreguntaTexto;
import ds.practica2.juegopreguntas.Preguntas.TipoPregunta;

/**
 * Created by bott1 on 14/04/2015.
 */
public abstract class GameManager {

    /**
     * Obtiene una pregunta del tipo especificado
     * @return
     */
    private static Pregunta getPregunta(){

        Pregunta p = new PreguntaTexto(TipoPregunta.TEXTO);

        // TODO Implementar

        return p;
    }
}
