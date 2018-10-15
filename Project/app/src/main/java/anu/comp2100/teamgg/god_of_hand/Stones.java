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
    @Nullable
    android.util.AttributeSet attrs;

    ArrayList<Stone> list;
    public  Stones(){
        list = new ArrayList<>();
    }
    public void registerStone(Stone s){
        list.add(s);
    }
    public void run(){   // use observer pattern
        for (Stone s : this.list){
            s.run();
        }
        Iterator<Stone> it = this.list.iterator();
        while (it.hasNext()){
            Stone s = it.next();
            if (s.y > GameActivity.screenHight){
                float w = GameActivity.screenWidth;
                float [] fl = {1/16 * w, 5/16 * w, 9/16 * w, 13/16 * w};
                int i = (int) Math.random() * fl.length;
                float y1 = (float) (Math.random() * GameActivity.screenHight/8);
                it.remove();
                Stone s1 = new Stone(context, attrs, fl[i], y1, s.width, s.length);
                this.registerStone(s1);
            }
        }
    }


    public void itemDraw(Canvas c, Paint p) {   // use observer pattern
        for (Stone s : this.list){
            s.itemDraw(c, p);

        }
    }

}