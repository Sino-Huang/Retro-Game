package anu.comp2100.teamgg.god_of_hand;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View implements Runnable, View.OnTouchListener{
    Playeregg leftEgg;
    Playeregg rightEgg;
    Handler timer;
    float screenHeight;
    float screenWidth;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        timer = new Handler();
        this.setOnTouchListener(this);
        timer.postDelayed(this, 10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_POINTER_DOWN) {
            if (motionEvent.getY() < screenWidth / 2) {
                leftEgg.pressed();
            } else {
                rightEgg.pressed();
            }
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_POINTER_UP) {
            if (motionEvent.getY() < screenWidth / 2) {
                leftEgg.unpressed();
            } else {
                rightEgg.unpressed();
            }
        }
        return true;
    }

    @Override
    public void run() {
        // update all parts
        this.invalidate();
        timer.postDelayed(this, 10);
    }

    public boolean collisionCheck() {
        return false;
    }
}
