package ds.practica2.juegopreguntas.preguntas;

import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by bott1 on 16/04/2015.
 */
public class PreguntaSonido extends Pregunta {

    private int refMultimedia;


    protected PreguntaSonido(TipoPregunta tipo, String titulo, int categoria, ArrayList<Pair<String, Integer>> respuestas, int _refMultimedia_, int dificultad) {
        super(tipo, titulo, categoria, respuestas, dificultad);

        refMultimedia = _refMultimedia_;
    }

    public int getRefMultimedia(){
        return refMultimedia;
    }
}
