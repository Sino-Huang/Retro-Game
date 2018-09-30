package anu.comp2100.teamgg.god_of_hand;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Goldegg extends Sprite {

    Bitmap goldegg;

    public Goldegg(Context context){
        this.goldegg = BitmapFactory.decodeResource(context.getResources(), R.drawable.goldenegg);
    }

    public Goldegg(float x, float y, float width, float height){
        this.item = new Item(x,y,width,height);
    }
    @Override
    public void run() {

    }

    @Override
    public void ItemDraw(Canvas c, Paint p) {
        int h = c.getHeight();
        int w = c.getWidth();

        int width = w/8;
        int height = h/16;

        c.drawBitmap(Bitmap.createScaledBitmap(this.goldegg, width, height, true), this.item.X, this.item.Y, p);

    }
}
