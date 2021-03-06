package anu.comp2100.teamgg.god_of_hand;
/**
 * @author COMP2100 TeamGG
 *  This GameView connects all of the core parts of the game. The items are drawn and run on this view.
 *  Multiple touch on the screen can be detected and the remaining time for special status of eggs is counted.
 */
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.Random;

public class GameView extends View implements Runnable, View.OnTouchListener{
    Handler timer;
    float screenHeight;
    float screenWidth;
    MovingItems movingitems;
    GameActivity activity;
    Paint p;
    int runCount = 0;
    int scoreCount = 0;
    int scoreRatio = 10;
    int effectRemaining = -1;
    PlayerEgg leftEgg;
    PlayerEgg rightEgg;
    static Random random = new Random();
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p.setTextSize(24);
        WeakReference<Context> thecon = new WeakReference<>(context);

        GameActivity activity = (GameActivity) getContext();
        this.activity = activity;
        screenHeight = activity.screenHeight;
        screenWidth = activity.screenWidth;
        leftEgg = new PlayerEgg(thecon.get(), attrs, screenWidth/4+30, 14*screenHeight/16, screenHeight/8, screenHeight/8, true, 1, screenWidth);
        rightEgg = new PlayerEgg(thecon.get(), attrs, screenWidth / 2 + 30, 14 * screenHeight / 16, screenHeight / 8, screenHeight / 8, false, 2, screenWidth);
        //System.out.println(screenWidth);
        timer = new Handler();
        movingitems = new MovingItems(screenHeight, screenWidth);
        movingitems.registerObservers(new MovingItem(thecon.get(), attrs, 30, 0, screenWidth / 8, screenHeight / 16, 0, screenHeight));
        movingitems.registerObservers(new MovingItem(thecon.get(), attrs, 4 * screenWidth / 8 + 30, 0, screenWidth / 8, screenHeight / 16, 2, screenHeight));
        movingitems.registerObservers(new MovingItem(thecon.get(), attrs, 2 * screenWidth / 8 + 30, screenHeight / 2, screenWidth / 8, screenHeight / 16, 1, screenHeight));
        movingitems.registerObservers(new MovingItem(thecon.get(), attrs, 6 * screenWidth / 8 + 30, screenHeight / 2, screenWidth / 8, screenHeight / 16, 3, screenHeight));
        this.setOnTouchListener(this);
        timer.postDelayed(this, 5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Resources res = getResources();
        Bitmap background = BitmapFactory.decodeResource(res, R.drawable.background);
        Bitmap resizebg = Bitmap.createScaledBitmap(background, canvas.getWidth(), canvas.getHeight(), true);
        canvas.drawBitmap(resizebg, 0, 0, p);
        for (int i = 0; i < 4; i++) {
            canvas.drawLine(canvas.getWidth()/ 4 * (i + 1), 0, canvas.getWidth()/ 4 * (i + 1), canvas.getHeight(), p);
        }
        movingitems.notifyObserversdraw(canvas, p);
        if (leftEgg.id == 3) {
            Paint paint = new Paint();
            paint.setTextSize(68);
            paint.setFakeBoldText(true);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("DIZZY!", canvas.getWidth() / 2, canvas.getHeight() / 2, paint);
        }
        canvas.drawText( activity.username + "'s Score: "+ String.valueOf(scoreCount) + (leftEgg.id == 2 ? "   × 3!!!!" :""), canvas.getWidth() / 12, canvas.getHeight() / 16, p);
        if (scoreCount < activity.highestValue){
            canvas.drawText("Highest Score: " + String.valueOf(activity.highestValue), canvas.getWidth() / 12, canvas.getHeight() / 16 + 40, p);
        }else {
            canvas.drawText("Highest Score: " + String.valueOf(scoreCount), canvas.getWidth() / 12, canvas.getHeight() / 16 + 40, p);
        }


        if (effectRemaining < 80 && effectRemaining > 0) {
            // blink effect when the effect is going to disappear
            if (effectRemaining % 10 > 5) {
                leftEgg.itemDraw(canvas, p);
                rightEgg.itemDraw(canvas, p);
            }
        } else {
            leftEgg.itemDraw(canvas,p);
            rightEgg.itemDraw(canvas,p);
        }

    }
    //
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int pointerid = motionEvent.getPointerId(motionEvent.getActionIndex());
        if (leftEgg != null) {
            if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN || motionEvent.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
                Log.w("DOWN", String.valueOf(pointerid));
                if (motionEvent.getX(motionEvent.getActionIndex()) < screenWidth / 2) {
                    if (leftEgg.id == 3) {
                        rightEgg.pressed(pointerid);
                    } else {
                        leftEgg.pressed(pointerid);
                    }
                } else {
                    if (leftEgg.id == 3) {
                        leftEgg.pressed(pointerid);
                    } else {
                        rightEgg.pressed(pointerid);
                    }
                }
            }
            if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP || motionEvent.getActionMasked() == MotionEvent.ACTION_POINTER_UP) {
                Log.w("UP", String.valueOf(pointerid));
                if (leftEgg.isPress) {
                    leftEgg.unpressed(pointerid);
                }
                if (rightEgg.isPress) {
                    rightEgg.unpressed(pointerid);
                }
            }
            return true;
        }
        return false;

    }


    @Override
    public void run() {
        // update all parts
        // decrease effect Remaining time
        if (effectRemaining > 0) {
            effectRemaining--;
        }
        runCount++;
        if (leftEgg.id == 2 && scoreRatio != 5) {
            scoreRatio = 5;
        } else if (leftEgg.id !=2 && scoreRatio != 10) {
            scoreRatio = 10;
        }
        if (runCount % scoreRatio == 0) {
            scoreCount++;
        }
        movingitems.notifyObserversrun();
        for (int i = 0; i < movingitems.observerList.size(); i++) {
            MovingItem getItem = (MovingItem) movingitems.observerList.get(i);
            //check leftegg
            if (leftEgg.col == getItem.col) {
                if (Math.abs(getItem.y - leftEgg.y) < 20) {
                    if (leftEgg.id != 1 && getItem.id == 0 ) {
                        activity.endGame();
                        return;
                    }else if (leftEgg.id != getItem.id && !(leftEgg.id == 1 && getItem.id == 0)) {
                        leftEgg.changeEgg(getItem.id);
                        rightEgg.changeEgg(getItem.id);
                        effectRemaining = 500;
                        break;
                    }
                }
            } else if (rightEgg.col == getItem.col) {   // check right egg
                if (Math.abs(getItem.y - rightEgg.y) < 20) {
                    if (leftEgg.id != 1 && getItem.id == 0) {
                        activity.endGame();
                        return;
                    }else if (leftEgg.id != getItem.id && !(leftEgg.id == 1 && getItem.id == 0)) {
                        rightEgg.changeEgg(getItem.id);
                        leftEgg.changeEgg(getItem.id);
                        effectRemaining = 500;
                        break;
                    }
                }
            }
        }
        // check effect remaining time
        if (effectRemaining == 0) {
            if (leftEgg.id != 0) {
                rightEgg.changeEgg(0);
                leftEgg.changeEgg(0);
                effectRemaining = -1;
            }
        }
        this.invalidate();
        timer.postDelayed(this, 5);
    }
}
