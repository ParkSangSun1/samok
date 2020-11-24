package com.example.tictactoe_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class settingActivity extends AppCompatActivity {

    private ImageView go_main;
    EditText player1;
    EditText player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        go_main = findViewById(R.id.go_main);

        go_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(settingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        /*
        ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(myIntent);
            }
        });
        */

    }
}