package com.example.connect_four;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity implements View.OnClickListener {

    Player p1, p2;
    private ImageButton[][] buttons = new ImageButton[4][4];
    private boolean player1Turn = true;
    private int roundCount;
    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private TextView textViewScore1;
    private TextView textViewScore2;
    private Button playAgain;
    private Button home;

    public Game(TextView textViewPlayer1, TextView textViewPlayer2, TextView textViewScore1, TextView textViewScore2, Button playAgain, Button home) {
        this.textViewPlayer1 = textViewPlayer1;
        this.textViewPlayer2 = textViewPlayer2;
        this.textViewScore1 = textViewScore1;
        this.textViewScore2 = textViewScore2;
        this.playAgain = playAgain;
        this.home = home;
    }

    public void setButtons(){
        for(int i=0;i<4;i++){
            for(int j=0 ;j<4;j++){
                String button_id = "imageButton" + i + j; //This is the button id in the layout, ex: imageButton01

                // Each button on layout has an id which is int, so i want to find this int value to use it in findViewById()
                int R_buttonId = getResources().getIdentifier(button_id,"id",getPackageName());
                buttons[i][j] = findViewById(R_buttonId);
                // logic is written in onClick() , we had also to implement View.OnClickListener
                Log.i("aa","i'm in the blue");

                buttons[i][j].setOnClickListener(this);
                buttons[i][j].setTag("0"); // 1 for blue, 2 for red, 0 for grey

            }
        }
    }

    @Override
    public void onClick(View view) {

        ImageButton clicked_button=(ImageButton)view;

        if(!(clicked_button.getTag().equals("0"))) {
            return;
        }

        if(player1Turn){
            clicked_button.setTag("1");
            clicked_button.setBackgroundResource(R.drawable.blue_btn);
        }
        else{
            clicked_button.setTag("2");
            clicked_button.setBackgroundResource(R.drawable.red_btn);
        }

        roundCount++;

        if(checkForWin()){
            if(player1Turn){
                player1wins();
            }
            else{
                player2wins();
            }
        }
        else if(roundCount==16){
            draw();
        }
        else{
            player1Turn = !player1Turn;
        }


    }

    private boolean checkForWin() {

        String[][] field = new String[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                field[i][j] = buttons[i][j].getTag().toString();
            }

        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (field[i][0].equals(field[i][1])&&field[i][0].equals(field[i][2])&&field[i][0].equals(field[i][3])&&!field[i][0].equals("0"))
                {
                    return true;
                }
                else if (field[0][i].equals(field[1][i])&&field[0][i].equals(field[2][i])&&field[0][i].equals(field[3][i])&&!field[0][i].equals("0")) {
                    return true;
                }
                else if (field[0][0].equals(field[1][1])&&field[0][0].equals(field[2][2])&&field[0][0].equals(field[3][3])&&!field[0][0].equals("0"))
                {
                    return true;
                }
                else if (field[3][0].equals(field[2][1])&&field[3][0].equals(field[1][2])&&field[3][0].equals(field[0][3])&&!field[3][0].equals("0"))
                {
                    return true;
                }


            }
//            return false;

        }
        return false;

    }


    private void player1wins() {
        player1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewScore1.setText("Player 1: " + player1Points);
        textViewScore2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                buttons[i][j].setTag("0");
                buttons[i][j].setBackgroundResource(R.drawable.grey_btn);
            }
        }

        roundCount =0;
        player1Turn = true;
    }




}
