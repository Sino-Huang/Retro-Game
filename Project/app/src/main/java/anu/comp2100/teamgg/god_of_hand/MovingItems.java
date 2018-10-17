package anu.comp2100.teamgg.god_of_hand;
/**
 * @author COMP2100 TeamGG
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Random;

public class MovingItems extends ArrayList<MovingItem> {

    float screenHeight;
    float screenWidth;

    ArrayList<MovingItem> list;
    public MovingItems(float screenHeight, float screenWidth){
        list = new ArrayList<>();
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;

    }
    public void registerStone(MovingItem s){
        list.add(s);
    }
    public void run(){   // use observer pattern
        MovingItems newStone = new MovingItems(screenHeight, screenWidth);
        float miny = screenHeight;
        for (MovingItem s : this.list) {
            if (s.y < miny) {
                miny = s.y;
            }
        }
        for (int i = 0; i < this.list.size(); i++) {
            this.list.get(i).run();
            if (this.list.get(i).y > screenHeight) {   // when a stone go beyond the screen, we need to remove that stone and generate a new stone
                WeakReference<Context> context = this.list.get(i).context;
                AttributeSet attrs = this.list.get(i).attrs;
                float w = screenWidth;
                float[] fl = new float[]{w/16, w/16*5, w/16*9, w/16*13};
                int r = GameView.random.nextInt(fl.length);
                float y1 = miny - screenHeight/2;
                miny = y1;
                MovingItem s1 = new MovingItem(context.get(), attrs, fl[r], y1, this.list.get(i).width, this.list.get(i).length, r, screenHeight);
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