package anu.comp2100.teamgg.god_of_hand;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View implements Runnable, View.OnTouchListener{
    Handler timer;
    float screenHeight;
    float screenWidth;
    Stones stones;
    Paint p;
    PlayerEgg leftEgg;
    PlayerEgg rightEgg;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();

        GameActivity activity = (GameActivity) getContext();
        screenHeight = activity.screenHight;
        screenWidth = activity.screenWidth;
        leftEgg = new PlayerEgg(context, attrs, screenWidth/4+30, 14*screenHeight/16, screenHeight/8, screenHeight/8, true);
        rightEgg = new PlayerEgg(context, attrs, screenWidth/2+30, 14*screenHeight/16, screenHeight/8, screenHeight/8, false);
        //System.out.println(screenWidth);
        timer = new Handler();
        stones = new Stones();
        stones.registerStone(new Stone(context,attrs,30,0,screenWidth/8,screenHeight/16));
        stones.registerStone(new Stone(context,attrs,4*screenWidth/8+30,0,screenWidth/8,screenHeight/16));
        stones.registerStone(new Stone(context,attrs,2*screenWidth/8+30,screenHeight/2,screenWidth/8,screenHeight/16));
        stones.registerStone(new Stone(context,attrs,6*screenWidth/8+30,screenHeight/2,screenWidth/8,screenHeight/16));
        this.setOnTouchListener(this);
        timer.postDelayed(this, 5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 4; i++) {
            canvas.drawLine(canvas.getWidth()/ 4 * (i + 1), 0, canvas.getWidth()/ 4 * (i + 1), canvas.getHeight(), p);
        }
        stones.itemDraw(canvas, p);
        leftEgg.itemDraw(canvas,p);
        rightEgg.itemDraw(canvas,p);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;

    }

    @Override
    public void run() {
        // update all parts
        stones.run();
        this.invalidate();
        timer.postDelayed(this, 5);
    }

}
