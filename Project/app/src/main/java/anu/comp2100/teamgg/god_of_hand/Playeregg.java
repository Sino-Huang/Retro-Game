package anu.comp2100.teamgg.god_of_hand;
/**
 * @author COMP2100 TeamGG
 *  The Playeregg is the core of the game God of Hand
 *  The status of the eggs can be changed according to the prop they touch
 *  The eggs can also response to the touch motion on the screen
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.lang.ref.WeakReference;

public class PlayerEgg extends Item {
    boolean left;
    float screenWidth;
    // The id indicates the status of current egg:
    // 1 for diamondegg , 2 for goldenegg , 3 for centuryegg
    int id = 0;
    Bitmap egg;
    Bitmap hand;
    int pointerId = -1;
    boolean isPress = false;
    WeakReference<Context> context;
    public PlayerEgg(Context context, @Nullable AttributeSet attrs, float x, float y, float width, float length, boolean left, int col, float screenWidth) {
        super(context, attrs, x, y, width, length, col);
        this.context = new WeakReference<>(context);
        this.left = left;
        if (context != null) {
            this.egg = BitmapFactory.decodeResource(this.context.get().getResources(), R.drawable.egg);
            this.screenWidth = screenWidth;
            if (left) {
                this.hand = BitmapFactory.decodeResource(this.context.get().getResources(), R.drawable.lefthand);
            } else {
                this.hand = BitmapFactory.decodeResource(this.context.get().getResources(), R.drawable.righthand);
            }
        }
    }

    // Change the status of egg if the egg touch a prop
    public void changeEgg(int id) {
        if (this.context.get() != null) {
            switch (id) {
                case 0:
                    this.egg = BitmapFactory.decodeResource(this.context.get().getResources(), R.drawable.egg);
                    this.id = 0;
                    break;
                case 1:
                    this.egg = BitmapFactory.decodeResource(this.context.get().getResources(), R.drawable.diamondegg);
                    this.id = 1;
                    break;
                case 2:
                    this.egg = BitmapFactory.decodeResource(this.context.get().getResources(), R.drawable.goldenegg);
                    this.id = 2;
                    break;
                case 3:
                    this.egg = BitmapFactory.decodeResource(this.context.get().getResources(), R.drawable.centuryegg);
                    this.id = 3;
                    break;
            }
        }
        this.id = id;
    }
    public void itemDraw(Canvas canvas, Paint paint){

        canvas.drawBitmap(Bitmap.createScaledBitmap(this.egg, (int)width, (int)length, true), this.x, this.y, paint );
        if (left) {
            canvas.drawBitmap(Bitmap.createScaledBitmap(this.hand, (int) (width*1.5), (int) length, true), this.x - screenWidth / 5, this.y, paint);
        } else {
            canvas.drawBitmap(Bitmap.createScaledBitmap(this.hand, (int) (width*1.5), (int) length, true), this.x + screenWidth / 8, this.y, paint);
        }
    }

    // When the egg is pressed, it will response according to its status
    public void pressed(int id){
        if (!isPress) {
            if (this.left){
                this.x = this.x - screenWidth/4;
                this.col = 0;
            }else {
                this.x = this.x + screenWidth/4;
                this.col = 3;
            }
            isPress = true;
            pointerId = id;
        }
    }
    // When a pressed egg is released, it will make response as well
    public void unpressed(int id){
        if (id == pointerId && isPress) {
            if (this.left){
                this.x = this.x + screenWidth/4;
                this.col = 1;
            }else {
                this.x = this.x - screenWidth/4;
                this.col = 2;
            }
            pointerId = -1;
            isPress = false;
        }
    }



}

