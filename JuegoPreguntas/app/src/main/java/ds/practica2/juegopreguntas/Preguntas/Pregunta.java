package ds.practica2.juegopreguntas.preguntas;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by bott1 on 14/04/2015.
 */
public class Pregunta {

    private String tituloPregunta;
    private TipoPregunta tipo;
    private int categoria;

    private ArrayList<Pair<String, Integer> > respuestas;


    // TODO eliminar deprecated
    @Deprecated
    Pregunta (TipoPregunta _nuevoTipo_){

        tipo = _nuevoTipo_;
    }

    protected Pregunta(TipoPregunta _tipo_, String _titulo_, int _categoria_, ArrayList<Pair<String, Integer> > _respuestas_) {
        // TODO
        tituloPregunta = _titulo_;
        tipo = _tipo_;
        categoria = _categoria_;
        respuestas = new ArrayList<>(_respuestas_);

        // Barajar preguntas para cambiar el orden
        Collections.shuffle(respuestas);

    }

    /*
    public ArrayList<Integer> getRespuestas() {
        return respuestasCorrectas;
    }
    */

    public TipoPregunta getTipo(){ return tipo;}

    public String getTituloPregunta(){ return tituloPregunta;}

    public int getCategoria() {
        return categoria;
    }
}
