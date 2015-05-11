package ds.practica2.juegopreguntas.popups;

import android.app.Activity;
import android.graphics.Point;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import ds.practica2.juegopreguntas.R;

/**
 * Created by bott1 on 11/05/2015.
 */
public class PopupFinJuegoResumen extends Popup {

    // Visuales
    private LinearLayout dock;
    private Button principal;


    /**
     *  @param activity
     * @param parent Optional - Can be null
     * @param _soluciones_
     */
    public PopupFinJuegoResumen(Activity activity, ViewGroup parent, ArrayList<Pair<String, String> > _soluciones_) {
        super.idPopupEspecifico = R.layout.resumen_juego;

        super.chargePopup(activity, parent);

        initComponents();
        mostrarSoluciones(_soluciones_);


    }

    private void mostrarSoluciones(ArrayList<Pair<String, String>> _soluciones_) {


        if(_soluciones_.size() > 0) {
            for (Pair<String, String> solucion : _soluciones_) {
                View elemento = LayoutInflater.from(actividad.getApplicationContext()).inflate(R.layout.elemento_resumen, null, false);

                TextView titulo = (TextView) elemento.findViewById(R.id.textResumenTitulo);
                TextView respuesta = (TextView) elemento.findViewById(R.id.textResumenRespuesta);

                titulo.setText(solucion.first);
                respuesta.setText(solucion.second);

                dock.addView(elemento);
            }
        }
        else
            ((TextView)super.layoutMarco.findViewById(R.id.textResumenFelicitacion)).setText("¡No has fallado ninguna!");
    }

    @Override
    public void showChargedPopup(Point p) {
        super.showChargedPopup(p);
    }


    protected void initComponents() {


        dock = (LinearLayout) super.layoutMarco.findViewById(R.id.linearLayoutDock);
        principal = (Button)super.layoutMarco.findViewById(R.id.botonListo);


    }

    public void setPrincipalListener(View.OnClickListener listener){
        principal.setOnClickListener(listener);
    }
}
