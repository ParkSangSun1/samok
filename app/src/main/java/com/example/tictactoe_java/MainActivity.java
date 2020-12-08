package com.example.tictactoe_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button [] buttons = new Button[100];
    private Button resetGame;
    int settingscore=0;
    String newwin1, newwin2;


    private int playerOneScoreCount, playerTwoScoreCount, rountCount;
    boolean activePlayer;


            int count = 0;
            //p1 = 0
            //p2 = 1
            // 2
            //x, o 인지 표시시
           int [] gameState = {2,2,2,2,2,2,2,2,2,2, 2,2,2,2,2,2,2,2,2,2, 2,2,2,2,2,2,2,2,2,2, 2,2,2,2,2,2,2,2,2,2, 2,2,2,2,2,2,2,2,2,2, 2,2,2,2,2,2,2,2,2,2, 2,2,2,2,2,2,2,2,2,2, 2,2,2,2,2,2,2,2,2,2, 2,2,2,2,2,2,2,2,2,2, 2,2,2,2,2,2,2,2,2,2};
            //이기는 경우의 수
            int [][] winningPositions ={
                    {0,1,2,3},{1,2,3,4},{2,3,4,5},{3,4,5,6},{4,5,6,7},{5,6,7,8},{6,7,8,9},  {10,11,12,13},{11,12,13,14},{12,13,14,15},{13,14,15,16},{14,15,16,17},{15,16,17,18},{16,17,18,19}, {20,21,22,23},{21,22,23,24},{22,23,24,25},{23,24,25,26},{24,25,26,27},{25,26,27,28},{26,27,28,29}, {30,31,32,33},{31,32,33,34},{32,33,34,35},{33,34,35,36},{34,35,36,37},{35,36,37,38},{36,37,38,39}, {40,41,42,43},{41,42,43,44},{42,43,44,45},{43,44,45,46},{44,45,46,47},{45,46,47,48},{46,47,48,49}, {50,51,52,53},{51,52,53,54},{52,53,54,55},{53,54,55,56},{54,55,56,57},{55,56,57,58},{56,57,58,59}, {60,61,62,63},{61,62,63,64},{62,63,64,65},{63,64,65,66},{64,65,66,67},{65,66,67,68},{66,67,68,69}, {70,71,72,73},{71,72,73,74},{72,73,74,75},{73,74,75,76},{74,75,76,77},{75,76,77,78},{76,77,78,79}, {80,81,82,83},{81,82,83,84},{82,83,84,85},{83,84,85,86},{84,85,86,87},{85,86,87,88},{86,87,88,89}, {90,91,92,93},{91,92,93,94},{92,93,94,95},{93,94,95,96},{94,95,96,97},{95,96,97,98},{96,97,98,99},//가로
                    {0,10,20,30},{10,20,30,40},{20,30,40,50},{30,40,50,60},{40,50,60,70},{50,60,70,80},{60,70,80,90}, {1,11,21,31},{11,21,31,41},{21,31,41,51},{31,41,51,61},{41,51,61,71},{51,61,71,81},{61,71,81,91}, {2,12,22,32},{12,22,32,42},{22,32,42,52},{32,42,52,62},{42,52,62,72},{52,62,72,82},{62,72,82,92}, {3,13,23,33},{13,23,33,43},{23,33,43,53},{33,43,53,63},{43,53,63,73},{53,63,73,83},{63,73,83,93}, {4,14,24,34},{14,24,34,44},{24,34,44,54},{34,44,54,64},{44,54,64,74},{54,64,74,84},{64,74,84,94}, {5,15,25,35},{15,25,35,45},{25,35,45,55},{35,45,55,65},{45,55,65,75},{55,65,75,85},{65,75,85,95}, {6,16,26,36},{16,26,36,46},{26,36,46,56},{36,46,56,66},{46,56,66,76},{56,66,76,86},{66,76,86,96}, {7,17,27,37},{17,27,37,47},{27,37,47,57},{37,47,57,67},{47,57,67,77},{57,67,77,87},{67,77,87,97}, {8,18,28,38},{18,28,38,48},{28,38,48,58},{38,48,58,68},{48,58,68,78},{58,68,78,88},{68,78,88,98}, {9,19,29,39},{19,29,39,49},{29,39,49,59},{39,49,59,69},{49,59,69,79},{59,69,79,89},{69,79,89,99},//세로
                    {6,17,28,39},{5,16,27,38},{16,27,38,49},{4,15,26,37},{15,26,37,48},{26,37,48,59},{3,14,25,36},{14,25,36,47},{25,36,47,58},{36,47,58,69},{2,13,24,35},{13,24,35,46},{24,35,46,57},{35,46,57,68},{46,57,68,79},{1,12,23,34},{12,23,34,45},{23,34,45,56},{34,45,56,67},{45,56,67,78},{56,67,78,89},{0,11,22,33},{11,22,33,44},{22,33,44,55},{33,44,55,66},{44,55,66,77},{55,66,77,88},{66,77,88,99},{10,21,32,43},{21,32,43,54},{32,43,54,65},{43,54,65,76},{54,65,76,87},{65,76,87,98},{20,31,42,53},{31,42,53,64},{42,53,64,75},{53,64,75,86},{64,75,86,97},{30,41,52,63},{41,52,63,74},{52,63,74,85},{63,74,85,96},{40,51,62,73},{51,62,73,84},{62,73,84,95},{50,61,72,83},{61,72,83,94},{60,71,82,93},  {3,12,21,30},{4,13,22,31},{13,22,31,40},{5,14,23,32},{14,23,32,41},{23,32,41,50},{6,15,24,33},{15,24,33,42},{24,33,42,51},{33,42,51,60},{7,16,25,34},{16,25,34,43},{25,34,43,52},{34,43,52,61},{43,52,61,70},{8,17,26,35},{17,26,35,44},{26,35,44,53},{35,44,53,62},{44,53,62,71},{53,62,71,80},{9,18,28,36},{18,27,36,45},{27,36,45,54},{36,45,54,63},{45,54,63,72},{54,63,72,81},{63,72,81,90},{19,28,37,46},{28,37,46,55},{37,46,55,64},{46,55,64,73},{55,64,73,82},{64,73,82,91},{29,38,47,56},{38,47,56,65},{47,56,65,74},{56,65,74,83},{65,74,83,92},{39,48,57,66},{48,57,66,75},{57,66,75,84},{66,75,84,93},{49,58,67,76},{58,67,76,85},{67,76,85,94},{59,68,77,86},{68,77,86,95},{69,78,87,96}//대각선
            };

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //권한 받아오기

        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        playerStatus = (TextView) findViewById(R.id.playerStatus);


        TextView player_one = (TextView) findViewById(R.id.playerOne);
        TextView player_two = (TextView) findViewById(R.id.playerTwo);
        TextView usersettingscore = (TextView) findViewById(R.id.user_settingscore);

        Intent intent = getIntent();
        String player1 = intent.getStringExtra("입력한 이름1");
        String player2 = intent.getStringExtra("입력한 이름2");
        String settingscore_btn = intent.getStringExtra("설정 스코어");

        player_one.setText(String.valueOf(player1));
        newwin1=player1;
        player_two.setText(String.valueOf(player2));
        newwin2=player2;
        usersettingscore.setText(String.valueOf(settingscore_btn));
        settingscore=Integer.parseInt(settingscore_btn);


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

            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#000000"));
            gameState[gameStatePointer]=0;
        }else{
            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#ffffff"));
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
        }else if(rountCount == 100){
            playAgain();
            Toast.makeText(this,"No Winner!", Toast.LENGTH_SHORT).show();

        }else{
            activePlayer =!activePlayer;
        }

        //현재 이기고 있는 사람
        if(playerOneScoreCount> playerTwoScoreCount){
            playerStatus.setText(newwin1+" 플레이어가 이기고 있습니다");
        }else if(playerTwoScoreCount>playerOneScoreCount){
            playerStatus.setText(newwin2+" 플레이어가 이기고 있습니다");
        }else{
            playerStatus.setText("");
        }

        if(playerOneScoreCount==settingscore){
            playerStatus.setText(newwin1+" 플레이어가 최종 우승했습니다");
        }
        else if(playerTwoScoreCount==settingscore){
            playerStatus.setText(newwin2+" 플레이어가 최종 우승했습니다");
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
                     gameState[winningPosion[1]]==gameState[winningPosion[2]] &&gameState[winningPosion[2]]==gameState[winningPosion[3]] &&
                     gameState[winningPosion[0]] != 2){
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