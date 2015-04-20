package ds.practica2.juegopreguntas.preguntas;

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
     * @param respuestas Lista de todas las respuestas
     * @param respuestasCorrectas Numero identificativo de las respuestas correctas
     * @return Pregunta
     */
    public Pregunta makePregunta( String titulo, CategoriaPregunta categoria, ArrayList<String> respuestas, ArrayList<Integer> respuestasCorrectas ){

        Pregunta pregunta = new Pregunta(TipoPregunta.DEFAULT, titulo, categoria, respuestas, respuestasCorrectas);
        return pregunta;

    }

    /**
     * Crea una pregunta con un recurso multimedia
     * @param tipoPregunta Tipo de la pregunta, definidos en TipoPregunta
     * @param titulo Pregunta en si misma
     * @param categoria Categoria a la que pertenece
     * @param respuestas Lista de todas las respuestas
     * @param respuestasCorrectas Numero identificativo de las respuestas correctas
     * @param refMultimedia Referencia del recurso multimedia.
     * @return Pregunta de tipo multimedia
     */
    public PreguntaMultimedia makePreguntaMultimedia(TipoPregunta tipoPregunta, String titulo, CategoriaPregunta categoria,
                                                     ArrayList<String> respuestas, ArrayList<Integer> respuestasCorrectas, String refMultimedia){

        PreguntaMultimedia pregunta = new PreguntaMultimedia(tipoPregunta, titulo, categoria, respuestas, respuestasCorrectas, refMultimedia);
        return pregunta;
    }
}
