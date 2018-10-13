package anu.comp2100.teamgg.god_of_hand;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Playeregg extends Sprite {

    Bitmap egg;
    int ID;
    float distance;
    boolean left;
    float width = GameActivity.screenWidth;
    float height = GameActivity.screenHight;

    public Playeregg(float x, float y, float width, float height, int ID, float distance, boolean left){
        this.item = new Item(x, y, width, height);
        this.ID = ID;
        this.distance = distance;
        this.left = left;
    }

    public Playeregg(Context context, int ID){
        if (ID == 1) {
            this.egg = BitmapFactory.decodeResource(context.getResources(), R.drawable.egg);
        }else if (ID == 2){
            this.egg = BitmapFactory.decodeResource(context.getResources(), R.drawable.diamondegg);
        }else if (ID == 3){
            this.egg = BitmapFactory.decodeResource(context.getResources(), R.drawable.goldenegg);
        }
    }

    @Override
    public void run() {
        this.item.Y += 1;
        this.distance += 1;
    }

    @Override
    public void ItemDraw(Canvas c, Paint p) {
        int h = c.getHeight();
        int w = c.getWidth();

        int width1 = w/8;
        int height1 = h/16;

        c.drawBitmap(Bitmap.createScaledBitmap(this.egg, width1, height1, true), this.item.X, this.item.Y, p);

    }

    public void pressed(){
        if (this.left == true){
            this.item.X = this.item.X - width/4;
        }else {
            this.item.X = this.item.X + width/4;
        }
        this.item.Y -= 1;


    }

    public void unpressed(){
        if (this.left == true){
            this.item.X = this.item.X + width/4;
        }else {
            this.item.X = this.item.X - width/4;
        }
        this.item.Y -= 1;
    }


}
