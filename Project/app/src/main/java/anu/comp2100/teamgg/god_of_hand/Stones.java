package anu.comp2100.teamgg.god_of_hand;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Stones extends ArrayList<Stone> {

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
            if(s.y >= s.length*8){
                s.y = 0;
            }
        }
    }


    public void itemDraw(Canvas c, Paint p) {   // use observer pattern
        for (Stone s : this.list){
            s.itemDraw(c, p);

        }
    }

}
