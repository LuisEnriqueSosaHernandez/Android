package com.example.lesh.notifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
@BindView(R.id.editTextTitle)
EditText editTextTitle;
@BindView(R.id.editTextMessage)
EditText editTextMessage;
@BindView(R.id.switchImportants)
Switch switchImportants;
private boolean isHighImportance=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Rigth after setContentView
        ButterKnife.bind(this);
    }
    @OnClick(R.id.buttonSend)
    public void click(Button btn)
    {
        sendNotification();
    }
    @OnCheckedChanged(R.id.switchImportants)
    public void change(CompoundButton buttonView, boolean isChecked){
        isHighImportance=isChecked;
        Toast.makeText(this,"working: "+isHighImportance,Toast.LENGTH_SHORT).show();
    }

    private void sendNotification(){
        Toast.makeText(this,"working",Toast.LENGTH_SHORT).show();
    }

}
