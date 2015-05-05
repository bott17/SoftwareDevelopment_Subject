package ds.practica2.juegopreguntas.manejadores;

import android.content.Context;
import android.util.Log;

import ds.practica2.juegopreguntas.sonidos.ReproductorCorto;

/**
 * Created by bott1 on 05/05/2015.
 */
public abstract class SonidoManager {

    private  static  final String TAG = "Sonido Manager";

    private static ReproductorCorto sonidosCortos;
    private static Context context;


    public static void load(Context _context_) {

        context = _context_;
        sonidosCortos = new ReproductorCorto(context);


    }

    public static void reproducirResultado(boolean acierto) {

        if(sonidosCortos.soundLoaded()) {
            if (acierto) {
                sonidosCortos.reproducirAcierto();
                Log.d(TAG, "Reproducido acierto");
            } else {
                sonidosCortos.reproducirFallo();
                Log.d(TAG, "Reproducido fallo");
            }
        }
    }
}
