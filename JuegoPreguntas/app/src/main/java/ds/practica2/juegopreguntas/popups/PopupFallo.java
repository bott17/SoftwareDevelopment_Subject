package ds.practica2.juegopreguntas.popups;

import android.app.Activity;
import android.graphics.Point;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ds.practica2.juegopreguntas.R;

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
    public void showChargedPopup(Point p) { super.showChargedPopup(p);
    }


    protected void initComponents() {

        botonContinuar = (Button)super.layoutMarco.findViewById(R.id.botonConinuar);
        botonFinalizarJuego = (Button)super.layoutMarco.findViewById(R.id.botonFinJuego);
    }

    public void setContinueListener(View.OnClickListener listener){
        botonContinuar.setOnClickListener(listener);
    }
    public void setFinalizarListener(View.OnClickListener listener){
        botonFinalizarJuego.setOnClickListener(listener);
    }
}