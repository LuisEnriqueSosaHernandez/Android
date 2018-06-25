package com.example.lesh.board.Activities;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lesh.board.R;

public class BoardActivity extends AppCompatActivity {
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        fab = findViewById(R.id.fabAddBoard);
        showAlertForCreatingBoard("title","message");
    }

    private void showAlertForCreatingBoard(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        if(!TextUtils.isEmpty(title))builder.setTitle(title);
        if(!TextUtils.isEmpty(message))builder.setMessage(message);

        View viewInflated=LayoutInflater.from(this).inflate(R.layout.dialog_create_board,null);
        builder.setView(viewInflated);

        final EditText input=viewInflated.findViewById(R.id.editTextNewBoard);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String boardName=input.getText().toString().trim();
                if(boardName.length()>0){
                    createNewBoard(boardName);
                }else{
                    Toast.makeText(getApplicationContext(), "The name is required to create a new Board", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    private void createNewBoard(String boardName) {

    }
}
