package com.example.connect_four;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Game extends View {
    ImageButton buttons[][] = new ImageButton[4][4];
    ImageButton homeBtn;

    int roundCount;

    Player p1;
    Player p2;

    public Game(Context context) {
        super(context);

    }
}
