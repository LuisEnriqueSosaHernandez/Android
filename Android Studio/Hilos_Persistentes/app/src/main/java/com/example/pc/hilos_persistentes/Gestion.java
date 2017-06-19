package com.example.pc.hilos_persistentes;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class Gestion extends AppCompatActivity {
    private Partida partida;
    private int dificultad;
    private int FPS=30;
    private Handler temporizador;
    private int botes;
    MediaPlayer golpeo;
    MediaPlayer fin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        botes=0;
        Bundle extras=getIntent().getExtras();
        dificultad=extras.getInt("DIFICULTAD");
        partida =new Partida(getApplicationContext(),dificultad);
        setContentView(partida);
        temporizador=new Handler();
        temporizador.postDelayed(elhilo,500);
        golpeo=MediaPlayer.create(this,R.raw.bote);
        fin=MediaPlayer.create(this,R.raw.over);
    }

    private Runnable elhilo=new Runnable() {
        @Override
        public void run() {
      if(partida.movimientoBola()){
          fin();
      }else{
          partida.invalidate();//elimina el contenido de image view y llama de neuvo a ondraw
            temporizador.postDelayed(elhilo,1000/FPS);
      }
        }
    };

    public boolean onTouchEvent(MotionEvent evento){
        int x=(int)evento.getX();
        int y=(int)evento.getY();

        if(partida.toque(x,y)){
            golpeo.start();
            botes++;
        }
        return false;
    }
public void fin(){
    temporizador.removeCallbacks(elhilo);
    fin.start();
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    Intent in =new Intent();
    in.putExtra("PUNTUACION",botes*dificultad);
    setResult(RESULT_OK,in);
    finish();//destruye la actividad actual
}
}
