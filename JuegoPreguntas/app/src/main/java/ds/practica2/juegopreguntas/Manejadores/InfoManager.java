package ds.practica2.juegopreguntas.manejadores;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;

import ds.practica2.juegopreguntas.EstadisticasPartida;
import ds.practica2.juegopreguntas.database.DBAdapter;
import ds.practica2.juegopreguntas.database.DBParams;
import ds.practica2.juegopreguntas.juego.TipoJuego;
import ds.practica2.juegopreguntas.preguntas.Pregunta;
import ds.practica2.juegopreguntas.preguntas.PreguntaFactoria;
import ds.practica2.juegopreguntas.preguntas.TipoPregunta;

/**
 * Created by bott1 on 14/04/2015.
 */
abstract  class InfoManager {

    private static String TAG = "InfoManager";

    private static DBAdapter mDbHelper;

    public static void startDB(Context contex){
        mDbHelper = new DBAdapter(contex);
        mDbHelper.createDatabase();
        mDbHelper.open();
    }

    public static ArrayList<Pregunta> getPreguntas(Context context, int numeroPreguntas, ArrayList<TipoPregunta> tiposDePreguntas) {

        Log.d(TAG, "Obteniendo preguntas...");

        ArrayList<Pregunta> preguntas = new ArrayList<>();

        Cursor preguntasCursor = mDbHelper.getPreguntas();


        if(preguntasCursor.moveToFirst()) {

            // TODO Controlar el tipo de pregunta

            // Recuperar titulo
            String title= preguntasCursor.getString(preguntasCursor.getColumnIndex("titulo"));
            int idCategoria = preguntasCursor.getInt(preguntasCursor.getColumnIndex("idcategoria"));
            int dificultad = preguntasCursor.getInt(preguntasCursor.getColumnIndex("dificultad"));
            int tipoPregunta = preguntasCursor.getInt(preguntasCursor.getColumnIndex("tipopregunta"));
            String recurso = preguntasCursor.getString(preguntasCursor.getColumnIndex("recurso"));

            ArrayList<Pair<String, Integer> > listaRespuestas  = new ArrayList<>();

            do {


                // Comprobacion: si el titulo cambia, crear pregunta e inicializar los campos de la pregunta
                if( !title.equals(preguntasCursor.getString(preguntasCursor.getColumnIndex("titulo")))) {
                    if(tipoPregunta != 3) { // Si no es una pregunta de sonido...
                        preguntas.add(PreguntaFactoria.makePregunta(title, idCategoria, listaRespuestas, dificultad));
                    }
                    else{

                        int idResource = context.getResources().getIdentifier(recurso, "raw", context.getPackageName());
                        preguntas.add(PreguntaFactoria.makePreguntaSonido(TipoPregunta.SONIDO, title, idCategoria, listaRespuestas, idResource, dificultad));
                    }


                    listaRespuestas  = new ArrayList<>();
                    title = (preguntasCursor.getString(preguntasCursor.getColumnIndex("titulo")));
                    tipoPregunta = preguntasCursor.getInt(preguntasCursor.getColumnIndex("tipopregunta"));
                    recurso = preguntasCursor.getString(preguntasCursor.getColumnIndex("recurso"));

                }

                // Aniando preguntas
                    listaRespuestas.add(new Pair<String, Integer>(preguntasCursor.getString(preguntasCursor.getColumnIndex("respuesta")), preguntasCursor.getInt(preguntasCursor.getColumnIndex("correcta"))));


                // TODO añadir categoria
                idCategoria = preguntasCursor.getInt(preguntasCursor.getColumnIndex("idcategoria"));
                dificultad = preguntasCursor.getInt(preguntasCursor.getColumnIndex("dificultad"));

            } while (preguntasCursor.moveToNext());

            // Crear la ultima pregunta, una vez el cursor ya ha finalizado
            if(tipoPregunta != 3) { // Si no es una pregunta de sonido...
                preguntas.add(PreguntaFactoria.makePregunta(title, idCategoria, listaRespuestas, dificultad));
            }
            else{
                int idResource = context.getResources().getIdentifier(recurso, "raw", context.getPackageName());
                preguntas.add(PreguntaFactoria.makePreguntaSonido(TipoPregunta.SONIDO, title, idCategoria, listaRespuestas, idResource, dificultad));
            }
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

    public static int getIdJuego() {

        Cursor resultado = mDbHelper.getIdJuego();

        return resultado.getInt(resultado.getColumnIndex("count(*)"));
    }

    public static void addJuego(TipoJuego tipoJuego) {

        // TODO aniadir tipo de juego
        mDbHelper.addJuego("NA");
    }

    /**
     * Obtiene un resumen de las estadisticas del jugador
     * @return Array con los resultados: Numero de partidas, numero de aciertos y numero de fallos
     */
    public static ArrayList<Integer> getResumen() {

        ArrayList<Integer> resumen = new ArrayList<>();

        Cursor resultado = mDbHelper.getNumPartidas();
        resumen.add(resultado.getCount());

        resultado = mDbHelper.getNumAciertos();
        resumen.add(resultado.getCount());

        resultado = mDbHelper.getNumFallos();
        resumen.add(resultado.getCount());

        return resumen;

    }
}