package itver.donapps.activitys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dmax.dialog.SpotsDialog;
import itver.donapps.R;
import itver.donapps.utils.Utils;

public class LoginActivity extends AppCompatActivity {

    //UI ELEMENTS
    private TextInputEditText txtEmail;
    private TextInputEditText txtPassword;

    private Button btnLogin;

    private TextView txtNewAccount;

    private Toolbar toolbar;

    private Switch switchRemember;
    private String email;
    private String password;
    //FIREBASE ELEMENTS
    private FirebaseAuth firebaseAuth;
    private DatabaseReference tableUser;

    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        tableUser = FirebaseDatabase.getInstance().getReference("users");
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        initComponents();
        setCredentials();
    }

    private void setCredentials(){
            String email=prefs.getString("email", "");
            String password= prefs.getString("password", "");
            if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)){
                txtEmail.setText(email);
                txtPassword.setText(password);
                switchRemember.setChecked(true);
            }else{
                switchRemember.setChecked(false);
            }
    }

    private void initComponents(){
        toolbar = (Toolbar) findViewById(R.id.login_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inicio de sesión");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtEmail = (TextInputEditText) findViewById(R.id.txtemail);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        txtNewAccount = (TextView) findViewById(R.id.txtnewaccount);
        switchRemember=(Switch) findViewById(R.id.switchRemember);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWithFirebase();

            }
        });


        txtNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GO TO REGISTER ACTIVITY
                Intent i  = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
private void Remember(String email,String password){
    if(switchRemember.isChecked()){
        SharedPreferences.Editor editor=prefs.edit();
        editor.putString("email",email);
        editor.putString("password",password);
        editor.commit();
        editor.apply();
    }else{
        SharedPreferences.Editor editor=prefs.edit();
        editor.putString("email","");
        editor.putString("password","");
        editor.commit();
        editor.apply();
    }
}
    private void loginWithFirebase(){
        btnLogin.setEnabled(false);
        final String email = txtEmail.getText().toString(),
                password = txtPassword.getText().toString();

        //check validations fields

        if(email.isEmpty() || !Utils.checkEmail(email)){
            Toast.makeText(this, "Porfavor introduce un correo válido", Toast.LENGTH_SHORT).show();
            btnLogin.setEnabled(true);
            return;
        }
        if(password.isEmpty()){
            Toast.makeText(this, "Porfavor introduce tu contraseña " +
                    "de almenos 6 caracteres", Toast.LENGTH_SHORT).show();
            btnLogin.setEnabled(true);
            return;
        }

        final AlertDialog waitingDialog = new SpotsDialog(LoginActivity.this);
        waitingDialog.show();
        waitingDialog.setMessage("Iniciando sesión...");

        //PROCESS LOGIN WITH FIREBASE
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        waitingDialog.dismiss();
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        btnLogin.setEnabled(true);
                        Remember(email,password);
                        startActivity(i);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                waitingDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Ha ocurrido un error en la autenticacion"
                        , Toast.LENGTH_LONG).show();
            }
        });

        btnLogin.setEnabled(true);

    }
}
