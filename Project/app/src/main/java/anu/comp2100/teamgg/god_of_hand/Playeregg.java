package anu.comp2100.teamgg.god_of_hand;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class Playeregg extends Item {
    boolean left;
    Context context;
    int ID = 0; // 1 for diamondegg , 2 for goldenegg , 3 for centuryegg
    Bitmap egg;
    Bitmap hand;
    boolean ispress = false;
    public Playeregg(Context context, @Nullable AttributeSet attrs, float x, float y, float width, float length, boolean left) {
        super(context, attrs, x, y, width, length);
        this.context = context;
        this.left = left;
        this.egg = BitmapFactory.decodeResource(context.getResources(), R.drawable.egg);
        if (left) {
            this.hand = BitmapFactory.decodeResource(context.getResources(), R.drawable.lefthand);
        } else {
            this.hand = BitmapFactory.decodeResource(context.getResources(), R.drawable.righthand);
        }
    }

    public void changeEgg(int id) {
        switch (id) {
            case 0:
                this.egg = BitmapFactory.decodeResource(context.getResources(), R.drawable.egg);
                break;
            case 1:
                this.egg = BitmapFactory.decodeResource(context.getResources(), R.drawable.diamondegg);
                break;
            case 2:
                this.egg = BitmapFactory.decodeResource(context.getResources(), R.drawable.goldenegg);
                break;
            case 3:
                this.egg = BitmapFactory.decodeResource(context.getResources(), R.drawable.centuryegg);
                break;
        }
        ID = id;
    }
    public void itemDraw(Canvas canvas, Paint paint){

        canvas.drawBitmap(Bitmap.createScaledBitmap(this.egg, (int)width, (int)length, true), this.x, this.y, paint );
        if (left) {
            canvas.drawBitmap(Bitmap.createScaledBitmap(this.hand, (int) (width*1.5), (int) length, true), this.x - GameActivity.screenWidth / 5, this.y, paint);
        } else {
            canvas.drawBitmap(Bitmap.createScaledBitmap(this.hand, (int) (width*1.5), (int) length, true), this.x + GameActivity.screenWidth / 8, this.y, paint);
        }
    }

    public void pressed(){
        if (this.left == true){
            this.x = this.x - GameActivity.screenWidth/4;
        }else {
            this.x = this.x + GameActivity.screenWidth/4;
        }
        ispress = true;
    }

    public void unpressed(){
        if (this.left == true){
            this.x = this.x + GameActivity.screenWidth/4;
        }else {
            this.x = this.x - GameActivity.screenWidth/4;
        }
        ispress = false;
    }



}

