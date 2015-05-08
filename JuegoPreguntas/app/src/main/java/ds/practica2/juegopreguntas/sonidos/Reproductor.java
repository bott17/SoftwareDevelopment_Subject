package ds.practica2.juegopreguntas.sonidos;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by bott1 on 08/05/2015.
 */
public class Reproductor {

    private Context context;
    private MediaPlayer mediaPlayer;
    private int resource;

    public Reproductor(Context _context_, int resourceId) {

        context = _context_;
        resource = resourceId;

        mediaPlayer = MediaPlayer.create(context, resourceId);
        mediaPlayer.setLooping(false);
    }

    public void playSound(){
        mediaPlayer.start();
    }

    public void stopSound(){
        mediaPlayer.pause();
        mediaPlayer.seekTo(0);
    }

    public boolean isPlaying(){
        return mediaPlayer.isPlaying();
    }

    public boolean isLooping(){
        return mediaPlayer.isLooping();
    }

    public void setLooping(boolean option){
        mediaPlayer.setLooping(option);

    }

    public void pause(){
        mediaPlayer.pause();
    }

    public int  getDuration() {
        return mediaPlayer.getDuration();
    }

    public int getCurrentPosition(){
        return mediaPlayer.getCurrentPosition();
    }
}
