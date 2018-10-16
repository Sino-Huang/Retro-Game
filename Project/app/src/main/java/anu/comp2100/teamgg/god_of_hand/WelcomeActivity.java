package anu.comp2100.teamgg.god_of_hand;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    String username;
    float Width;
    float Hight;
    WelcomeView view;
    Intent serv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        view = findViewById(R.id.welcomeView);
        serv = new Intent(this, MusicService.class);

    }

    public void startGame(View view) {
        // above are the things that we need to store the information and send to the next Game activity
        EditText editText = findViewById(R.id.editText);
        username = editText.getText().toString();
        if (username.equals("") || username.equals(" ")) {
            username = "Guest";
        }
        WelcomeView view1 = findViewById(R.id.welcomeView);
        // HighestValue = view1.ScoreBoard.get(0).value;
        startService(serv);

        Intent intent = new Intent(this, GameActivity.class);

        intent.putExtra("Width", Width);
        intent.putExtra("Hight", Hight);
        intent.putExtra("Username", username);
        intent.putExtra("HighestScore", this.view.heroscore);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        stopService(serv);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            int finalscore = data.getIntExtra("FinalScore", 0);
            if (finalscore > this.view.heroscore) {
                //change it
                Toast.makeText(this, "Congrats!! You hit a new record!", Toast.LENGTH_LONG).show();
                this.view.updateScore(username, finalscore);
            } else {
                Toast.makeText(this, "You lose! Try next time!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
