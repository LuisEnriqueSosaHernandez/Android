package lesh.egresatecnm.com.egresatecnm.Splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.facebook.AccessToken;
import lesh.egresatecnm.com.egresatecnm.Activities.MainActivity;
import lesh.egresatecnm.com.egresatecnm.Activities.inicioActivity;

public class splashActivity extends AppCompatActivity {
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(AccessToken.getCurrentAccessToken()==null){
            intent=new Intent(this, inicioActivity.class);

        }else{
            intent=new Intent(this, MainActivity.class);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);

    }
}
