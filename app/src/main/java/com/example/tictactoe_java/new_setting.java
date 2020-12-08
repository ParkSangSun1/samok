package com.example.tictactoe_java;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class new_setting extends AppCompatActivity {

    private Button go_main1;
    private Button player01;
    private Button player02;
    private Button settingscore_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_setting);

        go_main1 = findViewById(R.id.go_main1);
        player01 = findViewById(R.id.player01);
        player02 = findViewById(R.id.player02);
        settingscore_btn = findViewById(R.id.settingscore_btn);

        player01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText player_name01 = findViewById(R.id.player_name1);
                String name1 = player_name01.getText().toString();
                TextView player1vs = (TextView) findViewById(R.id.player1vs);
                player1vs.setText(name1);
                Toast.makeText(getApplicationContext(),name1+"님으로 설정",Toast.LENGTH_SHORT).show();

            }
        });

        player02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText player_name02 = findViewById(R.id.player_name2);
                String name2= player_name02.getText().toString();
                TextView player2vs = (TextView) findViewById(R.id.player2vs);
                player2vs.setText(name2);
                Toast.makeText(getApplicationContext(),name2+"님으로 설정",Toast.LENGTH_SHORT).show();
            }
        });

        settingscore_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText settingscore = findViewById(R.id.settingscore);
                String settingscore1 = settingscore.getText().toString();
                Toast.makeText(getApplicationContext(),settingscore1+"점으로 설정",Toast.LENGTH_SHORT).show();
            }
        });

        go_main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText player_name1 = (EditText) findViewById(R.id.player_name1);
                EditText player_name2 = (EditText) findViewById(R.id.player_name2);
                EditText settingscore = (EditText) findViewById(R.id.settingscore);

                String name1 = player_name1.getText().toString();
                String name2 = player_name2.getText().toString();
                String settingscore1 = settingscore.getText().toString();



                if (player_name1.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),"Player1 이름을 설정하세요!",Toast.LENGTH_SHORT).show();
                }
                else if (player_name2.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),"Player2 이름을 설정하세요!",Toast.LENGTH_SHORT).show();
                }
                else if (settingscore.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),"점수를 설정하세요!",Toast.LENGTH_SHORT).show();

                }
                else {
                    Intent intent = new Intent(new_setting.this, MainActivity.class);
                    intent.putExtra("입력한 이름1", name1);
                    intent.putExtra("입력한 이름2", name2);
                    intent.putExtra("설정 스코어", settingscore1);

                    startActivity(intent);
                    overridePendingTransition(R.anim.fadenin, R.anim.fadeout);
                }
            }
        });
    }
}