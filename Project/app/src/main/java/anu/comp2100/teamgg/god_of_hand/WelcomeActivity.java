package anu.comp2100.teamgg.god_of_hand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class WelcomeActivity extends AppCompatActivity {
    String username;
    int HighestValue;
    float Width;
    float Hight = (float)0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void startGame(View view) {
        // above are the things that we need to store the information and send to the next Game activity
        EditText editText = findViewById(R.id.editText);
        username = editText.getText().toString();
        if (username.equals("") || username.equals(" ")) {
            username = "Guest";
        }
        WelcomeView view1 = findViewById(R.id.welcomeView);
        HighestValue = view1.ScoreBoard.get(0).value;

        Intent intent = new Intent(this, GameActivity.class);

        intent.putExtra("Width", Width);
        intent.putExtra("Hight", Hight);
        intent.putExtra("Username", username);
        intent.putExtra("HighestScore", HighestValue);
        startActivity(intent);
    }
}
