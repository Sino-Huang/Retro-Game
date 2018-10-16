package anu.comp2100.teamgg.god_of_hand;

import android.annotation.SuppressLint;
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

    String heroname = "Guest";
    public int heroscore = 0;
    WelcomeActivity activity;
    Canvas canvas = null;
    Paint p;

    public WelcomeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // load the scoreboard information
        activity = (WelcomeActivity) getContext();
        p = new Paint();
        p.setTextSize(40);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.canvas == null) {
            this.canvas = canvas;
        }
        WelcomeActivity activity = (WelcomeActivity) getContext();
         activity.Hight = canvas.getHeight();
         activity.Width = canvas.getWidth();
        // draw background

        // draw scoreboard
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.score_board);
        Bitmap resizebm = Bitmap.createScaledBitmap(bitmap, (int) (canvas.getWidth() * 0.5), (int)(canvas.getHeight() * 0.5), true);
        canvas.drawBitmap(resizebm, (float)(canvas.getWidth() * 0.25), (float)(canvas.getHeight() * 0.4), p);
        canvas.drawText("Hero Board", (float) (canvas.getWidth() * 0.25) + 100, (float) (canvas.getHeight() * 0.4) + 130, p);
        canvas.drawText(String.valueOf(heroname), (float) (canvas.getWidth() * 0.25)+ 130, (float) (canvas.getHeight() * 0.4) + 250, p);
        canvas.drawText(String.valueOf(heroscore), (float) (canvas.getWidth() * 0.25)+ 130, (float) (canvas.getHeight() * 0.4) + 320, p);

    }

    public void updateScore(String username, int finalscore) {
        heroname = username;
        heroscore = finalscore;
        // save it
        save();
    }
    public void save(){
    }
}
