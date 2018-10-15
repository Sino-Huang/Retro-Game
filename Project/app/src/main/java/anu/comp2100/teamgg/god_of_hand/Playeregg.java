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
    Bitmap egg;
    public Playeregg(Context context, @Nullable AttributeSet attrs, float x, float y, float width, float length, boolean left) {
        super(context, attrs, x, y, width, length);
        this.left = left;
        this.egg = BitmapFactory.decodeResource(context.getResources(), R.drawable.egg);
    }
    public void itemDraw(Canvas canvas, Paint paint){
       canvas.drawBitmap(Bitmap.createScaledBitmap(this.egg, (int)width, (int)length, true), this.x, this.y, paint );
    }

    public void pressed(){
        if (this.left == true){
            this.x = this.x - GameActivity.screenWidth/4;
        }else {
            this.x = this.x + GameActivity.screenWidth/4;
        }
        this.y -= 1;
    }

    public void unpressed(){
        if (this.left == true){
            this.x = this.x + GameActivity.screenWidth/4;
        }else {
            this.x = this.x - GameActivity.screenWidth/4;
        }
        this.y -= 1;
    }



}

