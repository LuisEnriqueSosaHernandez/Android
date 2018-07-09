package com.example.lesh.multilenguaje;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String hola=getString(R.string.welcome);
        Toast.makeText(this,hola,Toast.LENGTH_SHORT).show();
    }
}
