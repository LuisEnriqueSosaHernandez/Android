package lesh.egresatecnm.com.egresatecnm.App;

import android.app.Application;
import android.os.SystemClock;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by pc on 08/09/2017.
 */

public class egresaTecNMApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppEventsLogger.activateApp(this);
        SystemClock.sleep(1500);

    }
}
