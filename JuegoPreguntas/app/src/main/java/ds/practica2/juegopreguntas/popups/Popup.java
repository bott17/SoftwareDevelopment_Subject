package ds.practica2.juegopreguntas.popups;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import ds.practica2.juegopreguntas.R;

/**
 * Created by bott1 on 10/05/2015.
 */
public abstract class Popup {

    private static final String TAG = "Popup";

    private FrameLayout marcoSubPopup;

    protected int idPopupEspecifico;

    public void showChargedPopup(final Activity actividad, Point p) {

        Log.d(TAG, "Mostrando popup ...");

        // Inflate the popup_layout.xml
        LayoutInflater layoutInflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutMarco = layoutInflater.inflate(R.layout.popup_base, null);

        PopupWindow popupWindow = new PopupWindow(actividad);
        popupWindow.setContentView(layoutMarco);
        popupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);

        // Some offset to align the popup a bit to the left, and a bit down, relative to button's position.
        int OFFSET_X = 0;
        int OFFSET_Y = 0;

        // Clear the default translucent background
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        marcoSubPopup = (FrameLayout) layoutMarco.findViewById(R.id.frameMarcoPopup);
        if(marcoSubPopup == null){
            Log.d(TAG, "marcoSubPopup es null");
        }
        else{
            Log.d(TAG, "marcoSubPopup encontrado!");
        }
        // Cargar layaout del popup especifico
        if(marcoSubPopup != null) {
            chargeLayoutPopupEspecifico(actividad.getApplicationContext());
        }

        // Displaying the popup at the specified location, + offsets.
        popupWindow.showAtLocation(layoutMarco, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
    }

    private void chargeLayoutPopupEspecifico(Context context){


        marcoSubPopup.removeAllViews();

        View viewPopup = LayoutInflater.from(context).inflate(idPopupEspecifico, null, false);

        marcoSubPopup.addView(viewPopup);


    }
}
