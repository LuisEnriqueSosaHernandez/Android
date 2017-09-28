package lesh.egresatecnm.com.navigationdrawer.Activities;

import android.support.v4.app.Fragment;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import lesh.egresatecnm.com.navigationdrawer.Fragments.AlertFragment;
import lesh.egresatecnm.com.navigationdrawer.Fragments.EmailFragment;
import lesh.egresatecnm.com.navigationdrawer.Fragments.InfoFragment;
import lesh.egresatecnm.com.navigationdrawer.R;

public class MainActivity extends AppCompatActivity {
private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView=(NavigationView)findViewById(R.id.navview);
        setFragmentByDefault();
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Toast.makeText(MainActivity.this, "Abierto", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(MainActivity.this, "Cerrado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               boolean  fragmentTransaction=false;
               Fragment fragment=null;
               switch (item.getItemId()){
                   case R.id.menu_mail:
                     fragment=new EmailFragment();
                       fragmentTransaction=true;
                       break;
                   case R.id.menu_alert:
                       fragment=new AlertFragment();
                       fragmentTransaction=true;
                       break;
                   case R.id.menu_info:
                       fragment=new InfoFragment();
                       fragmentTransaction=true;
                       break;
                   case R.id.menu_opcion1:
                       Toast.makeText(MainActivity.this, "Cerrando", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.menu_opcion2:
                       Toast.makeText(MainActivity.this, "Otro", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.menu_opcion3:
                       Toast.makeText(MainActivity.this, "Baia baia", Toast.LENGTH_SHORT).show();
                       break;
               }
               if(fragmentTransaction){
                  changeFragment(fragment,item);
                   drawerLayout.closeDrawers();

               }
               return true;
           }
       });
    }
    private void setToolbar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setFragmentByDefault(){
        changeFragment(new EmailFragment(),navigationView.getMenu().getItem(0));
    }
    private void changeFragment(Fragment fragment,MenuItem item){
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                //abrir el menu lateral
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
