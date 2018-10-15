package anu.comp2100.teamgg.god_of_hand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {
    static float screenWidth;
    static float screenHight;
    int highestValue;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        screenHight = intent.getFloatExtra("Hight", (float) 0.0);
        screenWidth = intent.getFloatExtra("Width", (float) 0.0);
        highestValue = intent.getIntExtra("HighestScore", 0);
        username = intent.getStringExtra("Username");
        System.out.println(screenWidth);
        setContentView(R.layout.activity_game);
    }
}
