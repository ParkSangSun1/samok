package com.example.tictactoe_java;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button [] buttons = new Button[100];
    private Button resetGame;

    private int playerOneScoreCount, playerTwoScoreCount, rountCount;
    boolean activePlayer;


            int count = 0;
            //p1 = 0
            //p2 = 1
            // 2
            //x, o 인지 표시시
           int [] gameState = {2,2,2,2,2,2,2,2,2,2,
                    2,2,2,2,2,2,2,2,2,2,
                    2,2,2,2,2,2,2,2,2,2,
                    2,2,2,2,2,2,2,2,2,2,
                    2,2,2,2,2,2,2,2,2,2,
                    2,2,2,2,2,2,2,2,2,2,
                    2,2,2,2,2,2,2,2,2,2,
                    2,2,2,2,2,2,2,2,2,2,
                    2,2,2,2,2,2,2,2,2,2,
                    2,2,2,2,2,2,2,2,2,2,};
            //이기는 경우의 수
            int [][] winningPositions ={
                    {0,1,2,3},{1,2,3,4},{2,3,4,5},{3,4,5,6},{4,5,6,7},{5,6,7,8},{6,7,8,9},  {10,11,12,13},{11,12,13,14},{12,13,14,15},{13,14,15,16},{14,15,16,17},{15,16,17,18},{16,17,18,19}, {20,21,22,23},{21,22,23,24},{22,23,24,25},{23,24,25,26},{24,25,26,27},{25,26,27,28},{26,27,28,29},//가로




                    {0,4,8},{4,8,12},{1,5,9},{5,9,13},{2,6,10},{6,10,14},{3,7,11},{7,11,15}, //세로
                    {1,6,11},{0,5,10},{5,10,15},{4,9,14},{2,5,8},{3,6,9},{6,9,12},{7,10,13}//대각선
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //권한 받아오기

        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        playerStatus = (TextView) findViewById(R.id.playerStatus);

        resetGame =(Button) findViewById(R.id.resetGame);

        for(int i=0;i<buttons.length;i++){
            String buttonID = "btn_"+i;
            int resourceID = getResources().getIdentifier(buttonID,"id",getPackageName());
            buttons[i] = (Button) findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }

        rountCount = 0;
        playerOneScoreCount=0;
        playerTwoScoreCount=0;
        activePlayer=true;

    }

    //클릭했을때 x 또는 o를 표시
    @Override
    public void onClick(View v) {

        if(!((Button)v).getText().toString().equals("")){
            return;
        }

        String buttonID = v.getResources().getResourceEntryName(v.getId()); //btn_2
        int gameStatePointer = Integer.parseInt(buttonID.replace("btn_",""));
        System.out.println(gameStatePointer);
        System.out.println("버튼아이디 :"+buttonID);

        if(activePlayer){

            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#FC4646"));
            gameState[gameStatePointer]=0;
        }else{
            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#467AFC"));
            gameState[gameStatePointer]=1;
        }
        rountCount++;


        //이기는 경우의 수가 되었을시 이긴사람 표시
        if(checkWinner()){
            if (activePlayer) {
                playerOneScoreCount++;
                updatePlayerScore();
                Toast.makeText(this,"Player One Win!",Toast.LENGTH_SHORT).show();
                playAgain();
            }else{
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(this,"Player Two Win!",Toast.LENGTH_SHORT).show();
                playAgain();
            }
        }else if(rountCount == 16){
            playAgain();
            Toast.makeText(this,"No Winner!", Toast.LENGTH_SHORT).show();

        }else{
            activePlayer =!activePlayer;
        }

        //현재 이기고 있는 사람
        if(playerOneScoreCount> playerTwoScoreCount){
            playerStatus.setText("Player One is Winning!");
        }else if(playerTwoScoreCount>playerOneScoreCount){
            playerStatus.setText("Player Two is Winning!");
        }else{
            playerStatus.setText("");
        }

        //리셋게임
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                playerStatus.setText("");
                updatePlayerScore();
            }
        });
     }

     //이겼을때
     public boolean checkWinner(){
         boolean winnerResult = false;
         //  : 이긴 경우수
         for(int []winningPosion : winningPositions){
             if(gameState[winningPosion[0]]==gameState[winningPosion[1]] &&
                     gameState[winningPosion[1]]==gameState[winningPosion[2]] && gameState[winningPosion[0]] != 2){
                 winnerResult = true;
             }
         }
         if(winnerResult) System.out.println("DEBUUUUG");
         return winnerResult;
     }

    public void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));

    }
    //다시 하기 눌렀을때
    public void playAgain(){
        rountCount = 0;
        activePlayer = true;

        for(int i=0;i<buttons.length;i++){
            gameState[i] =2;
            buttons[i].setText("");
        }
    }
}