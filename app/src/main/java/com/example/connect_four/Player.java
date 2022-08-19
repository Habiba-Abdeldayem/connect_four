package com.example.connect_four;

import androidx.appcompat.app.AppCompatActivity;

public class Player extends AppCompatActivity {
    private String name;
    private int playerPoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public void setPlayerPoints(int playerPoints) {
        this.playerPoints = playerPoints;
    }
}
