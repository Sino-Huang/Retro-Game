package anu.comp2100.teamgg.god_of_hand;
/**
 * @author COMP2100 TeamGG
 *  GameActivity receives intend from welcome activity and extract screen size for assigning values.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {
    float screenWidth;
    float screenHeight;
    int highestValue;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        screenHeight = intent.getFloatExtra("Height", (float) 0.0);
        screenWidth = intent.getFloatExtra("Width", (float) 0.0);
        highestValue = intent.getIntExtra("HighestScore", 0);
        username = intent.getStringExtra("Username");
        setContentView(R.layout.activity_game);
    }

    void endGame(){
        Intent intent = new Intent();
        GameView view = findViewById(R.id.gameView);
        intent.putExtra("FinalScore", view.scoreCount);
        setResult(AppCompatActivity.RESULT_OK, intent);
        finish();
    }
}
