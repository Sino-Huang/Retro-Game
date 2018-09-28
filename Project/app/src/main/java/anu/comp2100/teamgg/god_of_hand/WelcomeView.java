package anu.comp2100.teamgg.god_of_hand;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class WelcomeView extends View { // WelcomeView will contain the scoreboard information

    ScoreInfo ScoreBoard = new ScoreInfo();

    public WelcomeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // load the scoreboard information
        File file = new File("./res/scoreboard.json");
        if (file.exists()) {
            try {
                FileReader fr = new FileReader(file);
                Gson gson = new Gson();
                ScoreBoard = gson.fromJson(fr, ScoreInfo.class);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        WelcomeActivity activity = (WelcomeActivity) getContext();
        if (Math.abs(activity.Hight - (float)0.0) < 0.001) { // means they are not initialised yet
            activity.Hight = canvas.getHeight();
            activity.Width = canvas.getWidth();
        }
        // draw background

        // draw scoreboard
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.score_board);
        Bitmap resizebm = Bitmap.createScaledBitmap(bitmap, (int) (canvas.getWidth() * 0.5), (int)(canvas.getHeight() * 0.5), false);
        Paint p = new Paint();
        canvas.drawBitmap(resizebm, (float)(canvas.getWidth() * 0.25), (float)(canvas.getHeight() * 0.4), p);

    }
}
