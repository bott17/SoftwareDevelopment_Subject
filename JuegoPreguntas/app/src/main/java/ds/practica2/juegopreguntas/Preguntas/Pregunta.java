package ds.practica2.juegopreguntas.Preguntas;

/**
 * Created by bott1 on 14/04/2015.
 */
public abstract class Pregunta {

    TipoPregunta tipo;
    //Categoria categoria;


    // TODO aniadir la categoria
    Pregunta (TipoPregunta _nuevoTipo_){

        tipo = _nuevoTipo_;
    }
}
