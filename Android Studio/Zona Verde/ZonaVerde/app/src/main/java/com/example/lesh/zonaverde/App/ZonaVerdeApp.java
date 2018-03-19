package com.example.lesh.zonaverde.App;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by LESH on 14/03/2018.
 */

public class ZonaVerdeApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Metodo para dormir el splash , tiempo dado en ms
        SystemClock.sleep(2000);
    }
}
