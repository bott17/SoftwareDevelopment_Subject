package ds.practica2.juegopreguntas.Preguntas;

/**
 * Created by bott1 on 14/04/2015.
 */
public abstract class Pregunta {

    TipoPregunta tipo;
    private int respuesta;
    //private Categoria categoria;

    // TODO aniadir campos al constructor
    Pregunta (TipoPregunta _nuevoTipo_){

        tipo = _nuevoTipo_;
    }

    public int getRespuesta() {
        return respuesta;
    }
}
