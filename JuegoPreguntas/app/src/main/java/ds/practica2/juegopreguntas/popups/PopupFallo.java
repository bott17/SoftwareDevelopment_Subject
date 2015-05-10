package ds.practica2.juegopreguntas.popups;

import android.app.Activity;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import ds.practica2.juegopreguntas.R;

/**
 * Created by bott1 on 10/05/2015.
 */
public class PopupFallo extends Popup {

    public PopupFallo() {
        super.idPopupEspecifico = R.layout.aviso_fallo;
    }

    @Override
    public void showChargedPopup(Activity actividad, Point p) {
        super.showChargedPopup(actividad, p);
    }
}