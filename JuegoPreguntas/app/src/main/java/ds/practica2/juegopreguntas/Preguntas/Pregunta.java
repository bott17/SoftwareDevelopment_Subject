package ds.practica2.juegopreguntas.Preguntas;

import java.util.ArrayList;

/**
 * Created by bott1 on 14/04/2015.
 */
public abstract class Pregunta {

    private ArrayList<String> respuestas = new ArrayList<>();
    private TipoPregunta tipo;
    private int respuesta;
    //private Categoria categoria;

    // TODO aniadir campos al constructor
    Pregunta (TipoPregunta _nuevoTipo_){

        tipo = _nuevoTipo_;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public TipoPregunta getTipo(){ return tipo;}
}
