package com.heroes.lesh.kidneys.App;

import android.app.Application;
import android.os.SystemClock;

public class KidneysApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Metodo para dormir el splash , tiempo dado en ms
        SystemClock.sleep(0);
    }
}
