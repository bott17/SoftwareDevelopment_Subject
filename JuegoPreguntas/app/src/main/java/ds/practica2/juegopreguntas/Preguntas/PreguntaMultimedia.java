package ds.practica2.juegopreguntas.preguntas;

import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by bott1 on 16/04/2015.
 */
public class PreguntaMultimedia extends Pregunta {

    private String refMultimedia;


    protected PreguntaMultimedia(TipoPregunta tipo, String titulo, int categoria, ArrayList<Pair<String, Integer>> respuestas, String _refMultimedia_) {
        super(tipo, titulo, categoria, respuestas);

        refMultimedia = _refMultimedia_;
    }

    public String getRefMultimedia(){
        return refMultimedia;
    }
}
