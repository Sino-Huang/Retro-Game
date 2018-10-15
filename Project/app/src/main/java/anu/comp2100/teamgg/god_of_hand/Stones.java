package anu.comp2100.teamgg.god_of_hand;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;

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
            if (s.item.Y > GameActivity.screenHight){
                float w = GameActivity.screenWidth;
                float [] fl = {1/16 * w, 5/16 * w, 9/16 * w, 13/16 * w};
                int i = (int) Math.random() * fl.length;
                float y1 = (float) (Math.random() * GameActivity.screenHight/8);
                it.remove();
                Stone s1 = new Stone(fl[i], y1, s.item.width, s.item.length);
                this.add(s1);
            }
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
