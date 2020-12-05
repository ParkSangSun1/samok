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

                EditText player_name1 = (EditText) findViewById(R.id.player_name1);
                EditText player_name2 = (EditText) findViewById(R.id.player_name2);
                EditText settingscore = (EditText) findViewById(R.id.settingscore);

                String name1 = player_name1.getText().toString();
                String name2 = player_name2.getText().toString();
                String settingscore1 = settingscore.getText().toString();

                Intent intent= new Intent(settingActivity.this, MainActivity.class);
                intent.putExtra("입력한 이름1",name1);
                intent.putExtra("입력한 이름2",name2);
                intent.putExtra("설정 스코어",settingscore1);

                startActivity(intent);
            }
        });


    }


}