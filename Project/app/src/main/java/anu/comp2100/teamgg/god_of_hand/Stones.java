package anu.comp2100.teamgg.god_of_hand;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Stones extends ArrayList<Stone> {

    Context context;

    Random r = new Random();
    int a = r.nextInt(4) + 1;


    public void run(){
        for (Stone s : this){
            s.item.Y -= 1;
        }
        Iterator<Stone> it = this.iterator();
        while (it.hasNext()){
            Stone s = it.next();
            if (s.item.Y > GameActivity.screenHight)
                it.remove();
        }
    }


    public void ItemDraw(Canvas c, Paint p) {
        int h = c.getHeight();
        int w = c.getWidth();

        int width = w/8;
        int height = h/16;

        for (Stone s : this){
            s = new Stone(context, a);
            c.drawBitmap(Bitmap.createScaledBitmap(s.stoneImage, width, height, true), s.item.X, s.item.Y, p);
        }
    }

}
