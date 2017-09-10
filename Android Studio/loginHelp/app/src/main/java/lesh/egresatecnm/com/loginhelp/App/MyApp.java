package lesh.egresatecnm.com.loginhelp.App;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by pc on 09/09/2017.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(2000);
    }
}
