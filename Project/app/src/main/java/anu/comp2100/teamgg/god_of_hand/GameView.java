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

public class GameView extends View implements Runnable, View.OnTouchListener{
    Handler timer;
    float screenHeight;
    float screenWidth;
    MovingItems movingitems;
    Paint p;
    Playeregg leftEgg;
    Playeregg rightEgg;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();

        GameActivity activity = (GameActivity) getContext();
        screenHeight = activity.screenHight;
        screenWidth = activity.screenWidth;
        leftEgg = new Playeregg(context, attrs, screenWidth/4+30, 14*screenHeight/16, screenHeight/8, screenHeight/8, true);
        rightEgg = new Playeregg(context, attrs, screenWidth/2+30, 14*screenHeight/16, screenHeight/8, screenHeight/8, false);
        //System.out.println(screenWidth);
        timer = new Handler();
        movingitems = new MovingItems();
        movingitems.registerStone(new MovingItem(context,attrs,30,0,screenWidth/8,screenHeight/16));
        movingitems.registerStone(new MovingItem(context,attrs,4*screenWidth/8+30,0,screenWidth/8,screenHeight/16));
        movingitems.registerStone(new MovingItem(context,attrs,2*screenWidth/8+30,screenHeight/2,screenWidth/8,screenHeight/16));
        movingitems.registerStone(new MovingItem(context,attrs,6*screenWidth/8+30,screenHeight/2,screenWidth/8,screenHeight/16));
        this.setOnTouchListener(this);
        timer.postDelayed(this, 5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 4; i++) {
            canvas.drawLine(canvas.getWidth()/ 4 * (i + 1), 0, canvas.getWidth()/ 4 * (i + 1), canvas.getHeight(), p);
        }
        movingitems.itemDraw(canvas, p);
        leftEgg.itemDraw(canvas,p);
        rightEgg.itemDraw(canvas,p);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_POINTER_DOWN) {
            Log.w("TickPosis", String.valueOf(motionEvent.getX()));
            if (motionEvent.getX() < screenWidth / 2) {
                leftEgg.pressed();
            } else {
                rightEgg.pressed();
            }
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_POINTER_UP) {
            Log.w("TickPosis", String.valueOf(motionEvent.getX()));
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
        movingitems.run();
        this.invalidate();
        // check collision
        int outcome = checkCollision();
//        Log.w("COLLISION", String.valueOf(outcome));
        // effect happens
//        switch (outcome) {
//            case 0:
//                if (leftEgg.ID != 1) {
//                    // lose
//                    leftEgg.changeEgg(0);
//                    rightEgg.changeEgg(0);
//                }
//                break;
//            case 1:
//                // change
//                leftEgg.changeEgg(1);
//                rightEgg.changeEgg(1);
//                break;
//            case 2:
//                // change to golden egg
//                leftEgg.changeEgg(2);
//                rightEgg.changeEgg(2);
//                break;
//            case 3:
//                // change to centuryegg
//                leftEgg.changeEgg(3);
//                rightEgg.changeEgg(3);
//                break;
//        }
        timer.postDelayed(this, 5);
    }

    private int checkCollision() {
//        for (MovingItem i : movingitems) {
//            if (Math.abs(i.x - leftEgg.x) < 100) {
//                if (Math.abs(i.y - leftEgg.y) < 0.01) {
//                    return i.ID;
//                }
//            }
//            if (Math.abs(i.x - rightEgg.x) < 100) {
//                if (Math.abs(i.y - rightEgg.y) < 0.01) {
//                    return i.ID;
//                }
//            }
//        }
        return -1; // means no collision
    }

}
