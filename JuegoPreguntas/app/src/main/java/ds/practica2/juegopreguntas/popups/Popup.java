package ds.practica2.juegopreguntas.popups;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.text.Layout;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
    protected View layoutMarco;
    protected Activity actividad;
    private PopupWindow popupWindow;


    protected int idPopupEspecifico;

    protected void chargePopup(Activity _actividad_, ViewGroup parent){

        actividad = _actividad_;

        // Inflate the popup_layout.xml
        LayoutInflater layoutInflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutMarco = layoutInflater.inflate(R.layout.popup_base, parent);

        popupWindow = new PopupWindow(actividad);
        popupWindow.setContentView(layoutMarco);
        popupWindow.setFocusable(true);



        // Clear the default translucent background
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        // Crear un blocker para que el popup no desaparezca al pulsar fuera
        Display display = actividad.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        popupWindow.setWidth(width);
        popupWindow.setHeight(height);
        popupWindow.setOutsideTouchable(true);

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

        LinearLayout blocker = (LinearLayout) layoutMarco.findViewById(R.id.popupBase);
        blocker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public void showChargedPopup(Point p) {

        Log.d(TAG, "Mostrando popup ...");

        // Some offset to align the popup a bit to the left, and a bit down, relative to button's position.
        int OFFSET_X = 0;
        int OFFSET_Y = 0;

        // Displaying the popup at the specified location, + offsets.
        popupWindow.showAtLocation(layoutMarco, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
    }

    private void chargeLayoutPopupEspecifico(Context context){


        marcoSubPopup.removeAllViews();

        View viewPopup = LayoutInflater.from(context).inflate(idPopupEspecifico, null, false);

        marcoSubPopup.addView(viewPopup);


    }

    public void ocultarPopup(){
        Log.d(TAG, "Ocultar popup");
        popupWindow.dismiss();
    }

    protected abstract void initComponents();
}
