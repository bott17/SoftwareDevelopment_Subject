package ds.practica2.juegopreguntas.tipos;

/**
 * Created by bott1 on 12/05/2015.
 */
public abstract class TipoCategoria {

    public static String toString(int tipoCategoria){
        String resultado="";

        switch (tipoCategoria){
            case 3:
                resultado = "Deportes";
                break;
            case 1:
                resultado = "Ciencias";
                break;
            case 2:
                resultado = "Cine";
                break;
            default:
                resultado = "Ninguna";
                break;
        }
        return resultado;
    }
}
