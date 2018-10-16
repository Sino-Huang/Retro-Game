package anu.comp2100.teamgg.god_of_hand;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class GameView extends View implements Runnable, View.OnTouchListener{
    Handler timer;
    float screenHeight;
    float screenWidth;
    MovingItems movingitems;
    GameActivity activity;
    Paint p;
    int runCount = 0;
    int distanceCount = 0;
    int scoreRatio = 10;
    int effectRemaining = -1;
    Playeregg leftEgg;
    Playeregg rightEgg;
    static Random random = new Random();
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p.setTextSize(24);

        GameActivity activity = (GameActivity) getContext();
        this.activity = activity;
        screenHeight = activity.screenHight;
        screenWidth = activity.screenWidth;
        leftEgg = new Playeregg(context, attrs, screenWidth/4+30, 14*screenHeight/16, screenHeight/8, screenHeight/8, true, 1);
        rightEgg = new Playeregg(context, attrs, screenWidth / 2 + 30, 14 * screenHeight / 16, screenHeight / 8, screenHeight / 8, false, 2);
        //System.out.println(screenWidth);
        timer = new Handler();
        movingitems = new MovingItems();
        movingitems.registerStone(new MovingItem(context, attrs, 30, 0, screenWidth / 8, screenHeight / 16, 0));
        movingitems.registerStone(new MovingItem(context, attrs, 4 * screenWidth / 8 + 30, 0, screenWidth / 8, screenHeight / 16, 2));
        movingitems.registerStone(new MovingItem(context, attrs, 2 * screenWidth / 8 + 30, screenHeight / 2, screenWidth / 8, screenHeight / 16, 1));
        movingitems.registerStone(new MovingItem(context, attrs, 6 * screenWidth / 8 + 30, screenHeight / 2, screenWidth / 8, screenHeight / 16, 3));
        this.setOnTouchListener(this);
        timer.postDelayed(this, 5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 4; i++) {
            canvas.drawLine(canvas.getWidth()/ 4 * (i + 1), 0, canvas.getWidth()/ 4 * (i + 1), canvas.getHeight(), p);
        }
        canvas.drawText("Score: "+ String.valueOf(distanceCount) + (leftEgg.ID == 2 ? "   × 3!" :""), canvas.getWidth() / 12, canvas.getHeight() / 16, p);
        movingitems.itemDraw(canvas, p);
        if (leftEgg.ID == 3) {
            Paint paint = new Paint();
            paint.setTextSize(68);
            paint.setFakeBoldText(true);
            canvas.drawText("DIZZY!", (float) (canvas.getWidth() / 2.9), canvas.getHeight() / 2, paint);
        }

        if (effectRemaining < 80 && effectRemaining > 0) {
            if (effectRemaining % 10 > 5) { // blink effect when the effect is going to disappear
                leftEgg.itemDraw(canvas, p);
                rightEgg.itemDraw(canvas, p);
            }
        } else {
            leftEgg.itemDraw(canvas,p);
            rightEgg.itemDraw(canvas,p);
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_POINTER_DOWN) {
            if (motionEvent.getX() < screenWidth / 2) {
                if (leftEgg.ID == 3) {
                    rightEgg.pressed();
                } else {
                    leftEgg.pressed();
                }
            } else {
                if (leftEgg.ID == 3) {
                    leftEgg.pressed();
                } else {
                    rightEgg.pressed();
                }
            }
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_POINTER_UP) {
            if (leftEgg.ispress) {
                leftEgg.unpressed();
            }
            if (rightEgg.ispress) {
                rightEgg.unpressed();
            }
        }
        return true;
    }


    @Override
    public void run() {
        // update all parts
        // decrese effect Remaining time
        if (effectRemaining > 0) {
            effectRemaining--;
        }
        runCount++;
        if (leftEgg.ID == 2 && scoreRatio != 5) {
            scoreRatio = 5;
        } else if (leftEgg.ID !=2 && scoreRatio != 10) {
            scoreRatio = 10;
        }
        if (runCount % scoreRatio == 0) {
            distanceCount++;
        }
        movingitems.run();
        for (int i = 0; i < movingitems.list.size(); i++) {
            MovingItem getItem = movingitems.list.get(i);
            //check leftegg
            if (leftEgg.col == getItem.col) {
                if (Math.abs(getItem.y - leftEgg.y) < 20) {
                    if (leftEgg.ID != 1 && getItem.ID == 0 ) {
                        finish();
                    }else if (leftEgg.ID != getItem.ID && !(leftEgg.ID == 1 && getItem.ID == 0)) {
                        leftEgg.changeEgg(getItem.ID);
                        rightEgg.changeEgg(getItem.ID);
                        effectRemaining = 500;
                        break;
                    }
                }
            } else if (rightEgg.col == getItem.col) { // check right egg
                if (Math.abs(getItem.y - rightEgg.y) < 20) {
                    if (leftEgg.ID != 1 && getItem.ID == 0) {
                        finish();
                    }else if (leftEgg.ID != getItem.ID && !(leftEgg.ID == 1 && getItem.ID == 0)) {
                        rightEgg.changeEgg(getItem.ID);
                        leftEgg.changeEgg(getItem.ID);
                        effectRemaining = 500;
                        break;
                    }
                }
            }
        }
        // check effect remaining time
        if (effectRemaining == 0) {
            if (leftEgg.ID != 0) {
                rightEgg.changeEgg(0);
                leftEgg.changeEgg(0);
                effectRemaining = -1;
            }
        }
        this.invalidate();
        timer.postDelayed(this, 5);
    }
    void finish(){
        activity.finish();
    }

}
