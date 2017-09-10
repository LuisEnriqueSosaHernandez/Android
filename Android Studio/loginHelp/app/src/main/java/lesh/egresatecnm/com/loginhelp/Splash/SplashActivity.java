package lesh.egresatecnm.com.loginhelp.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import org.w3c.dom.Text;

import lesh.egresatecnm.com.loginhelp.Activities.LoginActivity;
import lesh.egresatecnm.com.loginhelp.Activities.MainActivity;
import lesh.egresatecnm.com.loginhelp.Util.Util;

public class SplashActivity extends AppCompatActivity {
private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs=getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        Intent intent=new Intent(this,LoginActivity.class);
        Intent intentMain=new Intent(this,MainActivity.class);

        if(!TextUtils.isEmpty(Util.getUserMailPreferences(prefs))&&!TextUtils.isEmpty(Util.getUserPasswordPreferences(prefs))){
            startActivity(intentMain);
        }else{
            startActivity(intent);
        }
            finish();

    }

}
