package ds.practica2.juegopreguntas.manejadores;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;

import ds.practica2.juegopreguntas.tipos.TipoJuego;
import ds.practica2.juegopreguntas.preguntas.Pregunta;
import ds.practica2.juegopreguntas.tipos.TipoPregunta;
import ds.practica2.juegopreguntas.tipos.TipoRespuestas;

/**
 * Created by bott1 on 14/04/2015.
 * Clase Singleton, solo puede haber una instancia de juego creada al mismo tiempo
 */
public class GameManager {

    private static String TAG = "GameManager";

    // Instancia singleton
    private static GameManager instance = null;

    private static int idPartida;
    private Context context;

    // Variables de juego
    private TipoJuego tipoJuego;
    private ArrayList<Pregunta> listaPreguntas;
    private int indiceJuego; // Indica la pregunta por la que va el jugador
    private int aciertosJugador;
    private ArrayList<Integer> indexDeFallos;


    /**
     * Inicia un juego del tipo especificado. Puede ser Default
     * @param _tipo_ Tipo de juego especificado en TipoJuego
     */
    private GameManager (TipoJuego _tipo_, Context _context_){

        tipoJuego = _tipo_;
        context = _context_;

        //InfoManager.startDB(context);

        idPartida = InfoManager.getIdJuego() + 1;
        Log.d(TAG, "Nº partidas anteriores: " + idPartida);

        initGame();
    }

    /**
     * Obtiene una instancia unica de juego
     * @param _tipo_ Tipo de juego elegido, definido en TipoJuego
     * @return Instancia del juego
     */
    public static GameManager getInstance (Context _context_, TipoJuego _tipo_){

        if(instance == null){
            instance = new GameManager(_tipo_, _context_);
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
        indexDeFallos = new ArrayList<>();

        // Variables comunes al tipo de juego
        int numeroPreguntas = 10;
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

        listaPreguntas = InfoManager.getPreguntas(context, numeroPreguntas, tiposDePreguntas);

        // Registrar el juego en la BD
        InfoManager.addJuego(tipoJuego);
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
     * @param indexSeleccionadas Indice de las respuestas seleccionadas
     * @return Pair con el primer campo que indica el resultado, en el segundo las respuestas correctas sin acertar
     */
    public Pair<Boolean, ArrayList<Integer> > validarPregunta(Pregunta pregunta, ArrayList<Integer> indexSeleccionadas){

        boolean correcto = false;
        Pair<Boolean, ArrayList<Integer> > resultado = new Pair<>(false, indexSeleccionadas);


        if(pregunta.getTipoRespuestas() == TipoRespuestas.MULTIPLE){
            // TODO Implementar Multirespuesta. No aplica en esta version
        }
        else if(pregunta.getTipoRespuestas() == TipoRespuestas.SIMPLE) {

            // Si la respuesta seleccionada es TRUE, entonces acerto la pregunta
            correcto = pregunta.getSolucionRespuesta(indexSeleccionadas.get(0));
            resultado = new Pair<>(correcto, indexSeleccionadas);
        }

        if(correcto){
            EstadisticasManager.updateAcierto(idPartida, pregunta);
        }
        else{
            // TODO pasar respuestas en vez de null. No aplica en esta version

            indexDeFallos.add(indiceJuego-1);

            EstadisticasManager.updateFallo(idPartida, pregunta, null);
        }


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

    public ArrayList<Pregunta> getPreguntasFallidas(){

        ArrayList<Pregunta> preguntasFallidas = new ArrayList<>();

        for(Integer index: indexDeFallos){
            preguntasFallidas.add(listaPreguntas.get(index));
        }

        return preguntasFallidas;
    }

}
