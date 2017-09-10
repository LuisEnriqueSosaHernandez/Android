package lesh.egresatecnm.com.loginhelp.Util;

import android.content.SharedPreferences;

/**
 * Created by pc on 09/09/2017.
 */

public class Util {
    public static String getUserMailPreferences(SharedPreferences preferences){
        return preferences.getString("email","");
    }
    public static String getUserPasswordPreferences(SharedPreferences preferences){
        return preferences.getString("password","");
    }
}
