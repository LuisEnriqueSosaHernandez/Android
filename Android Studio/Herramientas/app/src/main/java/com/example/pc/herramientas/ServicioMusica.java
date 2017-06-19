package com.example.pc.herramientas;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by pc on 17/06/2017.
 */

public class ServicioMusica extends Service{
MediaPlayer miReproductor;
    public void OnCreate(){
        super.onCreate();

        miReproductor=MediaPlayer.create(this,R.raw.reproducir);
        miReproductor.setLooping(true);
        miReproductor.setVolume(100,100);
    }
    public int OnStartCommand(Intent intent,int flags,int startId){
        miReproductor.start();
        return START_STICKY_COMPATIBILITY;
    }
    public void OnDestroy(){
        super.onDestroy();
        if(miReproductor.isPlaying()){
            miReproductor.stop();
        }
        miReproductor.release();
        miReproductor=null;
    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
