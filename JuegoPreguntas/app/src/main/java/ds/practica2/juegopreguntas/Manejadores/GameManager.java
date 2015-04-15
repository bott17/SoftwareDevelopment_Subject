package ds.practica2.juegopreguntas.Manejadores;

import android.util.Log;

import java.util.ArrayList;

import ds.practica2.juegopreguntas.Juego.TipoJuego;
import ds.practica2.juegopreguntas.Preguntas.Pregunta;
import ds.practica2.juegopreguntas.Preguntas.TipoPregunta;

/**
 * Created by bott1 on 14/04/2015.
 * Clase Singleton, solo puede haber una instancia de juego creada al mismo tiempo
 */
public class GameManager {

    private static String TAG = "GameManager";

    // Instancia singleton
    private static GameManager instance = null;

    // Variables de juego
    private TipoJuego tipoJuego;
    private ArrayList<Pregunta> listaPreguntas = new ArrayList<>();
    private int indiceJuego = 0; // Indica la pregunta por la que va el jugador
    private int aciertosJugador = 0;


    /**
     * Inicia un juego del tipo especificado. Puede ser Default
     * @param _tipo_ Tipo de juego especificado en TipoJuego
     */
    private GameManager (TipoJuego _tipo_){

        tipoJuego = _tipo_;

        initGame();
    }

    /**
     * Obtiene una instancia unica de juego
     * @param _tipo_ Tipo de juego elegido, definido en TipoJuego
     * @return Instancia del juego
     */
    public static GameManager getInstance (TipoJuego _tipo_){

        if(instance == null){
            instance = new GameManager(_tipo_);
        }

        return instance;
    }


    /**
     * Inicia el juego, segun el tipo de juego elegido
     */
    private void initGame() {

        Log.d(TAG, "Iniciando juego...");

        // Variables comunes al tipo de juego
        int numeroPreguntas = -1;
        int fallosPermitidos = -1;
        ArrayList<TipoPregunta> tiposDePreguntas = null;

        if(tipoJuego.equals(TipoJuego.DEFAULT)){

            numeroPreguntas = 15;
        }

        /* Ejemplo de otro tipo de juego, definiendo tipo de preguntas especificos
            if(tipoJuego.equals(TipoJuego.NOT_GAME)){

                numeroPreguntas = 12;
                tiposDePreguntas = new ArrayList<>();
                tiposDePreguntas.add(TipoPregunta.TEXTO);
                tiposDePreguntas.add(TipoPregunta.MULTIMEDIA);

            }
        */

        listaPreguntas = InfoManager.getPreguntas(numeroPreguntas, tiposDePreguntas);
    }


    /**
     * Devuelve la siguiente pregunta mientras quede alguna por responder
     * @return Pregunta restante, null en caso de que no quede ninguna
     */
    public Pregunta siguientePregunta(){

        if(indiceJuego < listaPreguntas.size()) {
            Log.d(TAG, "Siguiente pregunta obtenida...");
            indiceJuego++;
            return listaPreguntas.get(indiceJuego - 1);
        }
        else {
            Log.d(TAG, "No quedan mas preguntas...");
            return null;
        }
    }

    public boolean validarPregunta(Pregunta pregunta, int respuesta){

        boolean resultado = false;

        if(pregunta.getRespuesta() == respuesta){
            Log.d(TAG, "Respuesta correcta");
            // TODO
            resultado = true;
            aciertosJugador++;
            //EstadisticasManager.updateAcierto(pregunta);
        }
        else{
            Log.d(TAG, "Respuesta incorrecta");
            // TODO
            //EstadisticasManager.updateFallo(pregunta);
        }

        return resultado;

    }

    public void finJuego(){
        instance = null;
    }
}
