package ds.practica2.juegopreguntas.manejadores;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;

import ds.practica2.juegopreguntas.Estadisticas;
import ds.practica2.juegopreguntas.database.DBAdapter;
import ds.practica2.juegopreguntas.juego.TipoJuego;
import ds.practica2.juegopreguntas.preguntas.CategoriaPregunta;
import ds.practica2.juegopreguntas.preguntas.Pregunta;
import ds.practica2.juegopreguntas.preguntas.PreguntaFactoria;
import ds.practica2.juegopreguntas.preguntas.PreguntaTexto;
import ds.practica2.juegopreguntas.preguntas.TipoPregunta;

/**
 * Created by bott1 on 14/04/2015.
 */
public abstract class InfoManager {

    private static String TAG = "InfoManager";

    private static DBAdapter mDbHelper;

    public static void startDB(Context contex){
        mDbHelper = new DBAdapter(contex);
        mDbHelper.createDatabase();
    }

    public static ArrayList<Pregunta> getPreguntas(int numeroPreguntas, ArrayList<TipoPregunta> tiposDePreguntas) {

        Log.d(TAG, "Obteniendo preguntas...");

        ArrayList<Pregunta> preguntas = new ArrayList<>();

        mDbHelper.open();
        Cursor preguntasCursor = mDbHelper.getPreguntas();


        if(preguntasCursor.moveToFirst()) {
            // Recuperar titulo
            String title= preguntasCursor.getString(preguntasCursor.getColumnIndex("titulo"));
            int idCategoria = preguntasCursor.getInt(preguntasCursor.getColumnIndex("idcategoria"));

            ArrayList<Pair<String, Integer> > listaRespuestas  = new ArrayList<>();

            do {


                // Comprobacion: si el titulo cambia, crear pregunta e inicializar los campos de la pregunta
                if( !title.equals(preguntasCursor.getString(preguntasCursor.getColumnIndex("titulo")))) {
                    preguntas.add(PreguntaFactoria.makePregunta(title, idCategoria, listaRespuestas));

                    listaRespuestas  = new ArrayList<>();
                    title = (preguntasCursor.getString(preguntasCursor.getColumnIndex("titulo")));

                }

                // Aniando preguntas
                listaRespuestas.add(new Pair<String, Integer>(preguntasCursor.getString(preguntasCursor.getColumnIndex("respuesta")), preguntasCursor.getInt(preguntasCursor.getColumnIndex("correcta"))));

                // TODO añadir categoria
                idCategoria = preguntasCursor.getInt(preguntasCursor.getColumnIndex("idcategoria"));

            } while (preguntasCursor.moveToNext());

            // Crear la ultima pregunta, una vez el cursor ya ha finalizado
            preguntas.add(PreguntaFactoria.makePregunta(title, idCategoria, listaRespuestas));
        }
        return preguntas;
    }

    public static void updateAcierto(int idPartida, TipoPregunta tipo, int categoria) {
        mDbHelper.updateAcierto(idPartida, tipo, categoria);
    }

    public static void updateFallo(int idPartida, TipoPregunta tipo, int categoria) {
        mDbHelper.updateFallo(idPartida, tipo, categoria);

        Log.d(TAG, "Update fallo");

    }

    public Estadisticas getEstadisticas(){

        // TODO Obtener estadisticas de BD
        Estadisticas estadisticas = new Estadisticas();
        return estadisticas;

    }

    public static int getIdJuego() {

        Cursor resultado = mDbHelper.getIdJuego();

        return resultado.getInt(resultado.getColumnIndex("count(*)"));
    }

    public static void addJuego(TipoJuego tipoJuego) {

        // TODO aniadir tipo de juego
        mDbHelper.addJuego("NA");
    }
}
