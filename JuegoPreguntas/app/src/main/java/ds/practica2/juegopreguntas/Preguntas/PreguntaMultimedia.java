package ds.practica2.juegopreguntas.Preguntas;

import java.util.ArrayList;

/**
 * Created by bott1 on 16/04/2015.
 */
public class PreguntaMultimedia extends Pregunta {

    private String refMultimedia;


    protected PreguntaMultimedia(TipoPregunta tipo, String titulo, CategoriaPregunta categoria, ArrayList<String> respuestas, ArrayList<Integer> respuestasCorrectas, String _refMultimedia_) {
        super(tipo, titulo, categoria, respuestas, respuestasCorrectas);

        refMultimedia = _refMultimedia_;
    }

    public String getRefMultimedia(){
        return refMultimedia;
    }
}
