package anu.comp2100.teamgg.god_of_hand;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Playeregg extends Sprite implements View.OnTouchListener {

    Bitmap egg;

    public Playeregg(float x, float y, float width, float height){
        this.item = new Item(x, y, width, height);
    }

    public Playeregg(Context context){
        this.egg = BitmapFactory.decodeResource(context.getResources(), R.drawable.egg);
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

        c.drawBitmap(Bitmap.createScaledBitmap(this.egg, width, height, true), this.item.X, this.item.Y, p);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
