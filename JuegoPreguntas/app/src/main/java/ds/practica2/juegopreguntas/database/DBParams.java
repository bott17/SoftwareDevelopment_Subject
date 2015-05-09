package ds.practica2.juegopreguntas.database;

/**
 * Created by bott1 on 21/04/2015.
 */
public abstract class  DBParams {


    protected static final String sqlGetPreguntas = "";
    protected static final String sqlGetIDJuego = "select count(*) from partidas";
    protected  static final String sqlGetNumAciertos = "select* from estadisticas where acierto=1";
    protected  static final String sqlGetNumFallos = "select* from estadisticas where acierto=0";
    protected  static final String sqlGetNumParditas = "select * from partidas";

    private static final String tableCat_Col_Categoria = "categoria";

    public static final String tableEs_Col_IDJuego = "idjuego";
    public static final String tableEs_COl_IDCategoria = "idcategoria";
    public static final String tableEs_COl_IDTipo = "idTipo";
    public static final String tableEs_COl_Acierto = "acierto";



}
