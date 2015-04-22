package ds.practica2.juegopreguntas.manejadores;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;

import ds.practica2.juegopreguntas.juego.TipoJuego;
import ds.practica2.juegopreguntas.preguntas.Pregunta;
import ds.practica2.juegopreguntas.preguntas.TipoPregunta;

/**
 * Created by bott1 on 14/04/2015.
 * Clase Singleton, solo puede haber una instancia de juego creada al mismo tiempo
 */
public class GameManager {

    private static String TAG = "GameManager";

    // Instancia singleton
    private static GameManager instance = null;

    private static int idPartida;

    // Variables de juego
    private TipoJuego tipoJuego;
    private ArrayList<Pregunta> listaPreguntas;
    private int indiceJuego; // Indica la pregunta por la que va el jugador
    private int aciertosJugador;


    /**
     * Inicia un juego del tipo especificado. Puede ser Default
     * @param _tipo_ Tipo de juego especificado en TipoJuego
     */
    private GameManager (TipoJuego _tipo_){

        tipoJuego = _tipo_;

        // TODO recuperar id juego de la bd e incrementarlo
        idPartida = 1;

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

        indiceJuego = 0;
        aciertosJugador = 0;
        listaPreguntas  = new ArrayList<>();

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

    /**
     * Devuelve el resultado de la evaluación de la pregunta, incluyendo las respuestas que no se marcaron correctamente
     * @param pregunta Pregunta a evaluar
     * @param respuestas Respuestas a evaluar
     * @return Pair con el primer campo que indica el resultado, en el segundo las respuestas correctas sin acertar
     */
    public Pair<Boolean, ArrayList<Integer> > validarPregunta(Pregunta pregunta, ArrayList<Integer> respuestas){

        boolean correcto = false;

        ArrayList<Integer> respuestasPregunta = new ArrayList<>(pregunta.getRespuestas());

        for(Integer respuesta: respuestas){
            if(respuestasPregunta.contains(respuesta))
                respuestasPregunta.remove(respuesta);
        }

        if(respuestasPregunta.size() == 0 && pregunta.getRespuestas().size() == respuestas.size()){
            Log.d(TAG, "Respuesta correcta");
            correcto = true;
            aciertosJugador++;
            EstadisticasManager.updateAcierto(idPartida, pregunta);
        }
        else{
            Log.d(TAG, "Respuesta incorrecta");
            EstadisticasManager.updateFallo(idPartida, pregunta, respuestasPregunta);
        }

        Pair<Boolean, ArrayList<Integer> > resultado = new Pair<>(correcto,respuestasPregunta);

        return resultado;

    }

    public boolean checkNuevoJuego(){
        boolean newGame = false;

        if(indiceJuego == 0)
            newGame = true;

        return newGame;
    }

    public void finJuego(){
        instance = null;
    }

    public Pregunta getUltimaPregunta() {
        return listaPreguntas.get(indiceJuego-1);
    }

}
