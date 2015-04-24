package ds.practica2.juegopreguntas.preguntas;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import ds.practica2.juegopreguntas.tipos.TipoRespuestas;

/**
 * Created by bott1 on 14/04/2015.
 */
public class Pregunta {

    private String tituloPregunta;
    private TipoPregunta tipo;
    private int categoria;
    private TipoRespuestas tipoRespuestas;

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
        // TODO recuperar tipo respuesta d ela base de datos
        tipoRespuestas = TipoRespuestas.SIMPLE;


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

    public boolean getSolucionRespuesta(int indexRespuesta){

        return respuestas.get(indexRespuesta).second != 0 ; // Conversion integer to boolean
    }

    /**
     * Devuelve que tipo de respuesta hay que dar para contestar la pregunta
     * @return Tipo de respuesta que necesita la pregunta. (Multiple, simple....)
     */
    public TipoRespuestas getTipoRespuestas() { return tipoRespuestas;}

    public ArrayList<Pair<String, Integer>> getRespuestas() {
        return respuestas;
    }
}
