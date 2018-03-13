package iolesh.github.luisenriquesosahernandez.zonaverde;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by LESH on 12/03/2018.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(2000);
    }
}
