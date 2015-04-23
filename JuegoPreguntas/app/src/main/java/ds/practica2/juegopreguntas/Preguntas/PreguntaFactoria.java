package ds.practica2.juegopreguntas.preguntas;

import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by bott1 on 16/04/2015.
 *
 * Esta factoria encapsula toda la funcionalidad para crear preguntas.
 */
public abstract class PreguntaFactoria {

    /**
     * Crea una pregunta basica
     * @param titulo Pregunta en si misma
     * @param categoria Categoria a la que pertenece
     * @param respuestas Lista de todas las respuestas, cada una indica si es verdadera o falsa
     * @return Pregunta
     */
    public static Pregunta makePregunta( String titulo, int categoria, ArrayList<Pair<String, Integer>> respuestas){

        Pregunta pregunta = new Pregunta(TipoPregunta.DEFAULT, titulo, categoria, respuestas);
        return pregunta;

    }

    /**
     * Crea una pregunta con un recurso multimedia
     * @param tipoPregunta Tipo de la pregunta, definidos en TipoPregunta
     * @param titulo Pregunta en si misma
     * @param categoria Categoria a la que pertenece
     * @param respuestas Lista de todas las respuestas, cada una indica si es verdadera o falsa
     * @param refMultimedia Referencia del recurso multimedia.
     * @return Pregunta de tipo multimedia
     */
    public static PreguntaMultimedia makePreguntaMultimedia(TipoPregunta tipoPregunta, String titulo, int categoria,
                                                            ArrayList<Pair<String, Integer> > respuestas, String refMultimedia){

        PreguntaMultimedia pregunta = new PreguntaMultimedia(tipoPregunta, titulo, categoria, respuestas, refMultimedia);
        return pregunta;
    }
}
