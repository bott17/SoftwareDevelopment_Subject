package ds.practica2.juegopreguntas.popups;

import android.app.Activity;
import android.graphics.Point;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import ds.practica2.juegopreguntas.R;
import ds.practica2.juegopreguntas.activities.LanzadorActivity;

/**
 * Created by bott1 on 10/05/2015.
 */
public class PopupFallo extends Popup {

    private static final String TAG = "PopupFallo";
    private Button botonFinalizarJuego;
    private Button botonContinuar;

    /**
     *
     * @param activity
     * @param parent Optional - Can be null
     */
    public PopupFallo(Activity activity, ViewGroup parent) {
        super.idPopupEspecifico = R.layout.aviso_fallo;

        super.chargePopup(activity, parent);

        initComponents();


    }

    @Override
    public void showChargedPopup(Point p) {
        super.showChargedPopup(p);
    }

    @Override
    public void ocultarPopup() {
        Log.d(TAG, "ocultarPopup");
        super.ocultarPopup();
    }

    @Override
    protected void initComponents() {

        botonContinuar = (Button)super.layoutMarco.findViewById(R.id.botonConinuar);
        botonFinalizarJuego = (Button)super.layoutMarco.findViewById(R.id.botonFinJuego);

        botonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "boton continuar");
                PopupFallo.this.ocultarPopup();
            }
        });

        botonFinalizarJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO finalizar juego
            }
        });
    }
}