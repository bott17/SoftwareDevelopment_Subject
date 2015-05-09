package ds.practica2.juegopreguntas.database;

/**
 * Created by bott1 on 21/04/2015.
 */
public abstract class  DBParams {

    private static final String TABLE_NAME_ESTADISTICAS = "estadisticas";


    protected static final String sqlGetPreguntas = "select titulo, idcategoria, respuesta, dificultad, correcta, recurso, tipopregunta from preguntas left JOIN respuestas on preguntas.rowid=idpregunta";
    protected static final String sqlGetIDJuego = "select count(*) from partidas";
    protected  static final String sqlGetNumAciertos = "select* from estadisticas where acierto=1";
    protected  static final String sqlGetNumFallos = "select* from estadisticas where acierto=0";
    protected  static final String sqlGetNumParditas = "select * from partidas";

    private static final String PREGUNTAS_TITULO = "titulo";
    private static final String PREGUNTAS_IDCATEGORIA = "idcategoria";
    private static final String PREGUNTAS_TIPOPREGUNTA = "tipopregunta";
    private static final String PREGUNTAS_DIFICULTAD = "dificultad";
    private static final String PREGUNTAS_RECURSO = "recurso";


    private static final String RESPUESTAS_RESPUESTA = "respuesta";
    private static final String RESPUESTAS_CORRECTA = "correcta";

    private static final String ESTADISTICAS_IDJUEGO= "idjuego";
    private static final String ESTADISTICAS_IDCATEGORIA = "idcategoria";
    private static final String ESTADISTICAS_IDTIPO= "idtipo";
    private static final String ESTADISTICAS_ACIERTO= "acierto";


    public static String getTableNameEstadisticas() {
        return TABLE_NAME_ESTADISTICAS;
    }

    public static String getPreguntasTitulo() {
        return PREGUNTAS_TITULO;
    }

    public static String getPreguntasIdcategoria() {
        return PREGUNTAS_IDCATEGORIA;
    }

    public static String getPreguntasTipopregunta() {
        return PREGUNTAS_TIPOPREGUNTA;
    }

    public static String getPreguntasDificultad() {
        return PREGUNTAS_DIFICULTAD;
    }

    public static String getPreguntasRecurso() {
        return PREGUNTAS_RECURSO;
    }

    public static String getRespuestasRespuesta() {
        return RESPUESTAS_RESPUESTA;
    }

    public static String getRespuestasCorrecta() {
        return RESPUESTAS_CORRECTA;
    }

    public static String getEstadisticasIdjuego() {
        return ESTADISTICAS_IDJUEGO;
    }

    public static String getEstadisticasIdcategoria() {
        return ESTADISTICAS_IDCATEGORIA;
    }

    public static String getEstadisticasIdtipo() {
        return ESTADISTICAS_IDTIPO;
    }

    public static String getEstadisticasAcierto() {
        return ESTADISTICAS_ACIERTO;
    }
}
