package anu.comp2100.teamgg.god_of_hand;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Stones extends ArrayList<Stone> {

    Context context;
    Random randomgenerator = new Random();
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
        Stones newStone = new Stones();
        float miny = GameActivity.screenHight;
        for (Stone s : this.list) {
            if (s.y < miny) {
                miny = s.y;
            }
        }
        for (int i = 0; i < this.list.size(); i++) {
            this.list.get(i).run();
            if (this.list.get(i).y > GameActivity.screenHight) {   // when a stone go beyond the screen, we need to remove that stone and generate a new stone
                this.context = this.list.get(i).context;
                this.attrs = this.list.get(i).attrs;
                float w = GameActivity.screenWidth;
                float[] fl = new float[]{w/16, w/16*5, w/16*9, w/16*13};
                int r = randomgenerator.nextInt(fl.length);
//                Log.w("Standard", String.valueOf(miny - GameActivity.screenHight / 5));
//                Log.w("ADJ", String.valueOf(((float) randomgenerator.nextInt(10)) / 10 * (GameActivity.screenHight)/8));
                float y1 = miny - GameActivity.screenHight/2;
                miny = y1;
//                Log.w("NEWY", String.valueOf(y1));
                Stone s1 = new Stone(context, attrs, fl[r], y1, this.list.get(i).width, this.list.get(i).length);
                newStone.registerStone(s1);
            } else {
                newStone.registerStone(this.list.get(i));
            }
        }
        this.list = newStone.list;
    }


    public void itemDraw(Canvas c, Paint p) {   // use observer pattern
        for (Stone s : this.list){
            s.itemDraw(c, p);

        }
    }

}