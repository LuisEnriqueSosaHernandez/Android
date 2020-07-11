package itver.donapps.activitys;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import itver.donapps.R;
import itver.donapps.models.Cities;
import itver.donapps.models.City;
import itver.donapps.models.User;
import itver.donapps.utils.Utils;

public class RegisterActivity extends AppCompatActivity {


    //UI ELEMENTS
    private TextInputEditText txtName;
    private TextInputEditText txtLastName;
    private TextInputEditText txtAge;
    private TextInputEditText txtEmail;
    private TextInputEditText txtPassword;
    private TextInputEditText txtPhone;

    private Spinner spinnerGender;
    private Spinner spinnerBloodGroup;
    private Spinner spinnerState;
    private Spinner spinnerCategoria;

    private Button btnRegister;

    private Toolbar toolbar;

    //FIREBASE ELEMENTS
    private FirebaseAuth firebaseAuth;
    private DatabaseReference tableUsers;

    private int cityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        tableUsers = FirebaseDatabase.getInstance().getReference("users");
        initComponents();
    }

    private void initComponents() {
        cityID = 1;
        toolbar = (Toolbar) findViewById(R.id.register_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnRegister = (Button) findViewById(R.id.buttonRegister2);
        txtName = (TextInputEditText) findViewById(R.id.txtName);
        txtLastName = (TextInputEditText) findViewById(R.id.txtLastName);
        txtAge = (TextInputEditText) findViewById(R.id.txtAge);
        txtEmail = (TextInputEditText) findViewById(R.id.email);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
        txtPhone = (TextInputEditText) findViewById(R.id.phoneNumber);

        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        spinnerBloodGroup = (Spinner) findViewById(R.id.spinnerBloodGroup);
        spinnerState = (Spinner) findViewById(R.id.spinnerState);
        spinnerCategoria=(Spinner) findViewById(R.id.spinnerCategoria);


        //SPINNER GENERO
        ArrayAdapter<String> adapterSpinnerGender = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                Utils.getGenders());
        spinnerGender.setAdapter(adapterSpinnerGender);

        //SPINNER TIPO DE SANGRE
        ArrayAdapter<String> adapterSpinnerBloodGroup = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                Utils.getBloodGroups());
        spinnerBloodGroup.setAdapter(adapterSpinnerBloodGroup);

        //SPINNER CATEORIA
        ArrayAdapter<String> adapterSpinnerCategoria = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                Utils.getCategoria());
        spinnerCategoria.setAdapter(adapterSpinnerCategoria);

        //SPINNER ESTADOS DE LA REPUBLICA
        ArrayAdapter<City> adapterSpinnerStates = new ArrayAdapter<City>(this,
                android.R.layout.simple_spinner_dropdown_item, Cities.getCities());
        spinnerState.setPrompt("Elige tu ciudad");
        spinnerState.setAdapter(adapterSpinnerStates);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    btnRegister.setEnabled(false);
                    registerNewUser();
                    btnRegister.setEnabled(true);
            }
        });

        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                City city = (City) parent.getSelectedItem();
                cityID = city.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void registerNewUser() {
        try {
            final int age;
            final String name = txtName.getText().toString().toUpperCase();
            final String lastname = txtLastName.getText().toString().toUpperCase();
            final String email = txtEmail.getText().toString();
            final String password = txtPassword.getText().toString();
            final String gender = spinnerGender.getSelectedItem().toString();
            final String bloodGroup = spinnerBloodGroup.getSelectedItem().toString();
            final String phone = txtPhone.getText().toString();
            final String categoria=spinnerCategoria.getSelectedItem().toString();
            final String default_status = getString(R.string.default_status);

            //VALIDAR CAMPOS
            // CONSIDERAR CONTRASEÑA >= 6 CARACTERES, BUEN FORMATO DE CORREO ELECTRONICO
            if(name.isEmpty()){
                 Toast.makeText(this, "Porfavor introduce un nombre válido"
                        , Toast.LENGTH_SHORT).show();
                 return;
            }
            if (lastname.isEmpty()){

                Toast.makeText(this, "Porfavor introduce tus apellidos"
                        , Toast.LENGTH_SHORT).show();
                return;
            }
            age = Integer.parseInt(txtAge.getText().toString());
            if (age < 18 && age > 65){

                Toast.makeText(this, "Debes ser mayor de edad para ser donador"
                        , Toast.LENGTH_SHORT).show();
                return;
            }

            if (email.isEmpty() || !Utils.checkEmail(email)){

                Toast.makeText(this, "Porfavor introduce una direccion de correo válida"
                        , Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6||password.isEmpty()){

                Toast.makeText(this, "Porfavor introduce una contraseña de almenos 6 caracteres"
                        , Toast.LENGTH_SHORT).show();
                return;
            }

            if (gender.equals(Utils.getGenders()[0])){
                Toast.makeText(this, "Porfavor escoge un género"
                        , Toast.LENGTH_SHORT).show();
                return;
            }

            if (bloodGroup.equals(Utils.getBloodGroups()[0])){
                Toast.makeText(this, "Porfavor selecciona tu tipo de sangre"
                        , Toast.LENGTH_SHORT).show();
                return;

            }

            if (categoria.equals(Utils.getCategoria()[0])){
                Toast.makeText(this, "Porfavor selecciona tu categoría"
                        , Toast.LENGTH_SHORT).show();
                return;
            }

           //TRY TO CREATE NEW USER WITH EMAIL AND PASSWORD IN FIREBASE DATABASE
            final AlertDialog waitingDialog = new SpotsDialog(RegisterActivity.this);
            waitingDialog.show();
            waitingDialog.setMessage("Registrando...");
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            //SAVE USER INTO DB.
                            User newUser = new User(name, lastname, age, email,
                                    password, gender, bloodGroup,categoria,
                                    phone, cityID, default_status);
                            tableUsers.child(FirebaseAuth.getInstance().getCurrentUser()
                                    .getUid())
                                    .setValue(newUser)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            waitingDialog.dismiss();
                                            Toast.makeText(RegisterActivity.this,
                                                    "Registro completado con éxito",
                                                    Toast.LENGTH_LONG
                                            ).show();
                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            btnRegister.setEnabled(true);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            waitingDialog.dismiss();
                                            Toast.makeText(RegisterActivity.this,
                                                    "No se pudó completar el registro",
                                                    Toast.LENGTH_SHORT
                                            ).show();
                                        }
                                    });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    waitingDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "No se ha creado ningun usuario"
                                    +e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(this, "Porfavor introduce tu edad "
                    , Toast.LENGTH_SHORT).show();
            Log.e("Register error: ", e.getMessage());
        }

    }


}
