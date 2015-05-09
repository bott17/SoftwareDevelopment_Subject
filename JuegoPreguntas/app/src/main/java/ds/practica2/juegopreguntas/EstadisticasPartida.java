package ds.practica2.juegopreguntas;

import android.database.Cursor;

import java.util.ArrayList;

import ds.practica2.juegopreguntas.preguntas.CategoriaPregunta;

/**
 * Created by bott1 on 21/04/2015.
 */
public class EstadisticasPartida {

    private int aciertos, fallos, idPartida;
    private CategoriaPregunta categoria;

    public EstadisticasPartida(int aciertos, int fallos, int idPartida, CategoriaPregunta categoria) {
        this.aciertos = aciertos;
        this.fallos = fallos;
        this.idPartida = idPartida;
        this.categoria = categoria;
    }
}
