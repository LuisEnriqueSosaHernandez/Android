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

import dmax.dialog.SpotsDialog;
import itver.donapps.R;
import itver.donapps.models.Cities;
import itver.donapps.models.City;
import itver.donapps.models.User;
import itver.donapps.utils.Utils;

public class ProfileUpdateActivity extends AppCompatActivity {

    //UI ELEMENTS
    private TextInputEditText txtName;
    private TextInputEditText txtLastName;
    private TextInputEditText txtAge;
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
    private User u;
    private int cityID;
    private String gender;
    private String blood;
    private int city;
    private String categoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        firebaseAuth = FirebaseAuth.getInstance();
        tableUsers = FirebaseDatabase.getInstance().getReference("users");
        initComponents();
        changeData();
    }
    public void changeData(){
        if(u != null) {
            txtName.setText(u.getName());
            txtLastName.setText(u.getLastName());
            txtAge.setText(u.getAge().toString());
            txtPhone.setText(u.getPhone());
            gender=u.getGender();
            changeGender(gender);
            blood=u.getBloodGroup();
            changeBlood(blood);
            city=u.getCity();
            changeCity(city);
            categoria=u.getCategoria();
            changeCategoria(categoria);

        }
    }

    private void changeGender(String gender){
        switch (gender){
            case "Hombre":
                spinnerGender.setSelection(1);
                break;
            case "Mujer":
                spinnerGender.setSelection(2);
                    break;
        }
    }

    private void changeCategoria(String categoria){
        switch (categoria){
            case "Donador":
                spinnerCategoria.setSelection(1);
                break;
            case "Receptor":
                spinnerCategoria.setSelection(2);
                break;
        }
    }

    private void changeBlood(String blood){
        switch (blood){
            case "A+":
                spinnerBloodGroup.setSelection(1);
                break;
            case "A-":
                spinnerBloodGroup.setSelection(2);
                break;
            case "B+":
                spinnerBloodGroup.setSelection(3);
                break;
            case "B-":
                spinnerBloodGroup.setSelection(4);
                break;
            case "AB+":
                spinnerBloodGroup.setSelection(5);
                break;
            case "AB-":
                spinnerBloodGroup.setSelection(6);
                break;
            case "O+":
                spinnerBloodGroup.setSelection(7);
                break;
            case "O-":
                spinnerBloodGroup.setSelection(8);
                break;
        }
    }
private void changeCity(int city){
        switch (city){
            case 1:
                spinnerState.setSelection(0);
                break;
            case 2:
                spinnerState.setSelection(1);
                break;
            case 3:
                spinnerState.setSelection(2);
                break;
            case 4:
                spinnerState.setSelection(3);
                break;
            case 5:
                spinnerState.setSelection(4);
                break;
            case 6:
                spinnerState.setSelection(5);
                break;
            case 7:
                spinnerState.setSelection(6);
                break;
            case 8:
                spinnerState.setSelection(7);
                break;
            case 9:
                spinnerState.setSelection(8);
                break;
            case 10:
                spinnerState.setSelection(9);
                break;
            case 11:
                spinnerState.setSelection(10);
                break;
            case 12:
                spinnerState.setSelection(11);
                break;
            case 13:
                spinnerState.setSelection(12);
                break;
            case 14:
                spinnerState.setSelection(13);
                break;
            case 15:
                spinnerState.setSelection(14);
                break;
            case 16:
                spinnerState.setSelection(15);
                break;
            case 17:
                spinnerState.setSelection(16);
                break;
            case 18:
                spinnerState.setSelection(17);
                break;
            case 19:
                spinnerState.setSelection(18);
                break;
            case 20:
                spinnerState.setSelection(19);
                break;
            case 21:
                spinnerState.setSelection(20);
                break;
            case 22:
                spinnerState.setSelection(21);
                break;
            case 23:
                spinnerState.setSelection(22);
                break;
            case 24:
                spinnerState.setSelection(23);
                break;
            case 25:
                spinnerState.setSelection(24);
                break;
            case 26:
                spinnerState.setSelection(25);
                break;
            case 27:
                spinnerState.setSelection(26);
                break;
            case 28:
                spinnerState.setSelection(27);
                break;
            case 29:
                spinnerState.setSelection(28);
                break;
            case 30:
                spinnerState.setSelection(29);
                break;
            case 31:
                spinnerState.setSelection(30);
                break;
            case 32:
                spinnerState.setSelection(31);
                break;

        }
}
    private void initComponents() {
        u = Utils.getCurrentUser();
        cityID = 1;
        toolbar = (Toolbar) findViewById(R.id.toolbar_profile_update_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Perfil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnRegister = (Button) findViewById(R.id.buttonRegister2);
        txtName = (TextInputEditText) findViewById(R.id.txtName);
        txtLastName = (TextInputEditText) findViewById(R.id.txtLastName);
        txtAge = (TextInputEditText) findViewById(R.id.txtAge);
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
            final String email = u.getEmail();
            final String password = u.getPassword();
            final String gender = spinnerGender.getSelectedItem().toString();
            final String bloodGroup = spinnerBloodGroup.getSelectedItem().toString();
            final String categoria=spinnerCategoria.getSelectedItem().toString();
            final String phone = txtPhone.getText().toString();
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
            final AlertDialog waitingDialog = new SpotsDialog(ProfileUpdateActivity.this);
            waitingDialog.show();
            waitingDialog.setMessage("Actualizando...");
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
                                            Toast.makeText(ProfileUpdateActivity.this,
                                                    "Actualización completada con éxito",
                                                    Toast.LENGTH_LONG
                                            ).show();
                                            Intent intent = new Intent(ProfileUpdateActivity.this, HomeActivity.class);
                                            startActivity(intent);
                                            btnRegister.setEnabled(true);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            waitingDialog.dismiss();
                                            Toast.makeText(ProfileUpdateActivity.this,
                                                    "No se pudó completar la actualización",
                                                    Toast.LENGTH_SHORT
                                            ).show();
                                        }
                                    });
        }catch (Exception e){
            Toast.makeText(this, "Porfavor introduce tu edad "
                    , Toast.LENGTH_SHORT).show();
            Log.e("Register error: ", e.getMessage());
        }

    }





}
