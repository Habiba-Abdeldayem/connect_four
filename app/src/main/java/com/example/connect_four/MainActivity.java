package com.example.connect_four;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[4][4];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;
    // Later we will set button ids from R.drawable file , check onClick()
    private int greyBtn_id;
    private int blueBtn_id;
    private int redBtn_id;
    private String greyBtn_background;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private Button playAgain;
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = (TextView) findViewById(R.id.p1_textView);
        textViewPlayer2 = (TextView) findViewById(R.id.p2_textView);
        playAgain = (Button)findViewById(R.id.button_playAgain);
        home = (Button)findViewById(R.id.button_home);
    }


    // we have 16 buttons, we want to be able to control these buttons in order to change color & check winner
    // so we fill the array of buttons with the buttons found on the layout using a loop instead of setting it manually
    public void setButtons(){
        for(int i=0;i<4;i++){
            for(int j=0 ;j<4;j++){
                String button_id = "imageButton" + i + j; //This is the button id in the layout, ex: imageButton01

                // Each button on layout has an id which is int, so i want to find this int value to use it in findViewById()
                int R_buttonId = getResources().getIdentifier(button_id,"id",getPackageName());
                buttons[i][j] = findViewById(R_buttonId);
                // logic is written in onClick() , we had also to implement View.OnClickListener
                buttons[i][j].setOnClickListener(this);
                greyBtn_background = findViewById(R.id.imageButton00).getResources().toString();
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
            Log.i("blue","i'm in the blue");
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
        else if(roundCount==9){
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
                if (field[i][0].equals(field[i][1])&&field[i][0].equals(field[i][2])&&!field[i][0].equals(greyBtn_background))
                {
                    return true;
                }
                else if (field[0][i].equals(field[1][i])&&field[0][i].equals(field[2][i])&&!field[0][i].equals(greyBtn_background)) {
                    return true;
                }
                else if (field[0][0].equals(field[1][1])&&field[0][0].equals(field[2][2])&&field[0][0].equals(field[3][3])&&!field[0][0].equals(greyBtn_background))
                {
                    return true;
                }
                else if (field[3][0].equals(field[2][1])&&field[3][0].equals(field[1][2])&&field[3][0].equals(field[0][3])&&!field[3][0].equals(greyBtn_background))
                {
                    return true;
                }


            }

        }
        return false;
    }


    private void player1wins() {
    }
    private void player2wins() {
    }
    private void draw() {
    }
}