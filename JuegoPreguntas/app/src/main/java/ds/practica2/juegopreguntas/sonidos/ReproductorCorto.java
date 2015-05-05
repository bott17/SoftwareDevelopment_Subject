package ds.practica2.juegopreguntas.sonidos;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import ds.practica2.juegopreguntas.R;

/**
 * Created by bott1 on 05/05/2015.
 */
public class ReproductorCorto {

    private boolean loaded = false;
    private SoundPool soundPool;
    private int sonidoAcierto, sonidoFallo;

    public ReproductorCorto(Context context) {

        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                loaded = true;
            }
        });

        sonidoAcierto = soundPool.load(context, R.raw.acierto, 1);
        sonidoFallo = soundPool.load(context, R.raw.fallo,1);

    }

    public boolean soundLoaded(){
        return loaded;
    }

    public void reproducirAcierto() {
        soundPool.play(sonidoAcierto, 1, 1, 1, 0, 1f);
    }

    public void reproducirFallo() {
        soundPool.play(sonidoFallo, 1, 1, 1, 0, 1f);
    }
}
