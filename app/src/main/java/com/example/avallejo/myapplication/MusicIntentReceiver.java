package com.example.avallejo.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;

/**
 * Created by Avallejo on 13/02/2018.
 */

public class MusicIntentReceiver extends BroadcastReceiver{

    private Context _context;
    private Intent _intent;
    private boolean _activate = false;
    private MediaPlayer mediaPlayer ;

    public MusicIntentReceiver(){
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        _context= context;
        _intent = intent;
        if(mediaPlayer == null)
            mediaPlayer = MediaPlayer.create(_context, R.raw.alert);
        DisconectAuriculares();
    }

    public void DisconectAuriculares() {
        if (_activate) {
            if ( _intent != null && _intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                int state = _intent.getIntExtra("state", -1);
                if (state == 1) {
                    Utilidades.mostrarToastText(_context, "Audifonos Conectados");
                    mediaPlayer.stop();
                } else {
                    mediaPlayer = MediaPlayer.create(_context, R.raw.alert);
                    mediaPlayer.start();
                    mediaPlayer.setVolume(1f, 1f);
                    mediaPlayer.setLooping(true);
                    Utilidades.mostrarToastText(_context, "Audifonos Desconectados");
                    MyService.Mimail();
                    new MyService();
                }
            }
        } else {
            if (mediaPlayer.isLooping()) {
                mediaPlayer.setLooping(false);
            }
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            if (mediaPlayer != null)
                mediaPlayer.release();
        }
    }
    public void setActivate(boolean activate){
        _activate = activate;
    }
}
