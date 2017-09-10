package lesh.egresatecnm.com.loginhelp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import lesh.egresatecnm.com.loginhelp.R;
import lesh.egresatecnm.com.loginhelp.Util.Util;

public class LoginActivity extends AppCompatActivity {
    private EditText txtemail;
    private EditText txtpassword;
    private Switch srecordar;
    private Button btnentrar;
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefs=getSharedPreferences("Preferences",Context.MODE_PRIVATE);

        binUI();
        setCredentialsIfExist();
        btnentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=txtemail.getText().toString();
                String pass=txtpassword.getText().toString();
                if(login(email,pass)){
                    goToMain();
                    saveOnPreferences(email,pass);
                }
            }
        });
    }

    private void setCredentialsIfExist() {
        String email=Util.getUserMailPreferences(prefs);
        String password= Util.getUserPasswordPreferences(prefs);
        if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)){
            txtemail.setText(email);
            txtpassword.setText(password);
        }
    }

    private void binUI(){
         txtemail=(EditText)findViewById(R.id.txtEmail);
        txtpassword=(EditText)findViewById(R.id.txtpassword);
         srecordar=(Switch)findViewById(R.id.srecordar);
         btnentrar=(Button)findViewById(R.id.btnentrar);
    }
    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password){
        return password.length()>=4;
    }
    private boolean login(String email,String password){
        if(!isValidEmail(email)){
            Toast.makeText(this,"Email is not valid,please try again",Toast.LENGTH_SHORT).show();
            return false;
        }else if(!isValidPassword(password)){
            Toast.makeText(this,"Password is not valid 4 characters of more",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }

    }
    private void saveOnPreferences(String email,String password){
        if(srecordar.isChecked()){
            SharedPreferences.Editor editor=prefs.edit();
            editor.putString("email",email);
            editor.putString("password",password);
            editor.commit();
            editor.apply();
        }
    }
    private void goToMain(){
        Intent intent=new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
