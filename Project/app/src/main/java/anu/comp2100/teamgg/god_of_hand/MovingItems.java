package anu.comp2100.teamgg.god_of_hand;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Random;

public class MovingItems extends ArrayList<MovingItem> {

    Context context;
    @Nullable
    android.util.AttributeSet attrs;

    ArrayList<MovingItem> list;
    public MovingItems(){
        list = new ArrayList<>();
    }
    public void registerStone(MovingItem s){
        list.add(s);
    }
    public void run(){   // use observer pattern
        MovingItems newStone = new MovingItems();
        float miny = GameActivity.screenHight;
        for (MovingItem s : this.list) {
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
                int r = GameView.random.nextInt(fl.length);
                float y1 = miny - GameActivity.screenHight/2;
                miny = y1;
                MovingItem s1 = new MovingItem(context, attrs, fl[r], y1, this.list.get(i).width, this.list.get(i).length, r);
                newStone.registerStone(s1);
            } else {
                newStone.registerStone(this.list.get(i));
            }
        }
        this.list = newStone.list;
    }


    public void itemDraw(Canvas c, Paint p) {   // use observer pattern
        for (MovingItem s : this.list){
            s.itemDraw(c, p);

        }
    }

}