package com.example.tictactoe_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class new_setting extends AppCompatActivity {

    private Button go_mainn;
    EditText player1;
    EditText player2;
    Button player_btn01;
    Button player_btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_setting);

        go_mainn = findViewById(R.id.go_main1);
        player_btn01 = findViewById(R.id.player01);
        player_btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText player1 = (EditText) findViewById(R.id.player_name1);
                String name1 = player1.getText().toString();
                Toast.makeText(getApplicationContext(),name1+"님 으로 설정 되었습니다",Toast.LENGTH_SHORT).show();
            }
        });

    }
}