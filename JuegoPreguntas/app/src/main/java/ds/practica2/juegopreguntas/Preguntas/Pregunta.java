package ds.practica2.juegopreguntas.Preguntas;

import java.util.ArrayList;

/**
 * Created by bott1 on 14/04/2015.
 */
public class Pregunta {

    private TipoPregunta tipo;
    private CategoriaPregunta categoria;

    private ArrayList<String> respuestas;
    private ArrayList<Integer> respuestasCorrectas;


    // TODO eliminar deprecated
    @Deprecated
    Pregunta (TipoPregunta _nuevoTipo_){

        tipo = _nuevoTipo_;
    }

    protected Pregunta(TipoPregunta _tipo_, String _titulo_, CategoriaPregunta _categoria_, ArrayList<String> _respuestas_, ArrayList<Integer> _respuestasCorrectas_) {
        // TODO
        tipo = _tipo_;
        categoria = _categoria_;
        respuestas = new ArrayList<>(_respuestas_);
        respuestasCorrectas = new ArrayList<>(_respuestasCorrectas_);

    }

    public ArrayList<Integer> getRespuestas() {
        return respuestasCorrectas;
    }

    public TipoPregunta getTipo(){ return tipo;}
}
