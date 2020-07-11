package itver.donapps.activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import itver.donapps.R;
import itver.donapps.utils.Utils;
import itver.donapps.fragments.HomeFragment;
import itver.donapps.fragments.InfoFragment;
import itver.donapps.fragments.ProfileFragment;
import itver.donapps.fragments.SearchFragment;
import itver.donapps.models.User;

public class HomeActivity extends AppCompatActivity {

    //FIREBASE ELEMENTS
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private DatabaseReference tableUsers;

    //UI ELEMENTS
    private Toolbar toolbar;
    private BottomNavigationViewEx bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        initComponents();
    }

    @Override
    public void onStart() {
        super.onStart();
        //CHECK IF THE USER IS LOGGIN, IF NOT SIGNOUT..
       currentUser = firebaseAuth.getCurrentUser();
        if(currentUser==null){
            signOut();
        }else{
            createGlobalCurrentUser();
        }

    }


    private void initComponents(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                                new HomeFragment()).commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar_home_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Donapps");

        bottomNav = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationView);
        bottomNav.enableAnimation(false);
        bottomNav.enableItemShiftingMode(false);
        bottomNav.enableShiftingMode(false);
        bottomNav.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.ic_home:
                        getSupportActionBar().setTitle("Donapps");
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.ic_search:
                        getSupportActionBar().setTitle("Buscar donares");
                        selectedFragment = new SearchFragment();
                        break;
                    case R.id.ic_profile:
                        getSupportActionBar().setTitle("Perfil");
                        selectedFragment = new ProfileFragment();
                        break;
                    case R.id.ic_info:
                        getSupportActionBar().setTitle("Información");
                        selectedFragment = new InfoFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,selectedFragment)
                        .commit();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.menuLogout){
            FirebaseAuth.getInstance().signOut();
            signOut();
        }

        return true;
    }


    private void signOut(){
        firebaseAuth.signOut();
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void createGlobalCurrentUser(){
        //Create the current user
        String uid = currentUser.getUid();
        tableUsers = FirebaseDatabase.getInstance().getReference("users").child(uid);
        tableUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User u = dataSnapshot.getValue(User.class);
                Utils.createCurrentUser(u);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
