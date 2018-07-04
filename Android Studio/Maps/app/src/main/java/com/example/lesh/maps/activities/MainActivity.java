package com.example.lesh.maps.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lesh.maps.R;
import com.example.lesh.maps.fragments.MapFragment;
import com.example.lesh.maps.fragments.WelcomeFragment;

public class MainActivity extends AppCompatActivity {
Fragment currentFragment;

    //Se llama cada vez que rota
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null){
            currentFragment=new WelcomeFragment();
            changeFragment(currentFragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_welcome:
                currentFragment=new WelcomeFragment();

            break;
            case R.id.menu_map:
                currentFragment=new MapFragment();
                break;
        }
        changeFragment(currentFragment);
        return super.onOptionsItemSelected(item);
    }
    private void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container,fragment).commit();
    }
}
