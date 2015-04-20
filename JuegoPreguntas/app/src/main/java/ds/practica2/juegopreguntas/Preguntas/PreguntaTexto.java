package ds.practica2.juegopreguntas.preguntas;

/**
 * Created by bott1 on 14/04/2015.
 */
public class PreguntaTexto extends Pregunta{

    private String tituloPregunta = "DEFAULT_QUESTION";


    public PreguntaTexto(String _titulo_) {

        super(TipoPregunta.TEXTO);
        tituloPregunta = _titulo_;

    }

    public String getTituloPregunta(){ return tituloPregunta;}
}
