package anu.comp2100.teamgg.god_of_hand;
/**
 * @author COMP2100 TeamGG
 *  MoviingItems is a subject that can register its observer and inform them once needed.
 *  For this part we use observer pattern to decrease coupling.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MovingItems {

    float screenHeight;
    float screenWidth;

    ArrayList<Observer> observerList;
    public MovingItems(float screenHeight, float screenWidth){
        observerList = new ArrayList<>();
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;

    }
    // Register an observer
    public void registerObservers(Observer s){
        observerList.add(s);
    }

    // use observer pattern and this method is to notify all registered observer
    public void notifyObserversrun(){
        MovingItems newStone = new MovingItems(screenHeight, screenWidth);
        float miny = screenHeight;
        for (Observer s : this.observerList) {
            if ( ((MovingItem)s).y < miny) {
                miny = ((MovingItem)s).y;
            }
        }
        for (int i = 0; i < this.observerList.size(); i++) {
            this.observerList.get(i).run();
            // when a movingItem goes beyond the screen, we need to remove that and generate a new item randomly
            if (((MovingItem)this.observerList.get(i)).y > screenHeight) {
                WeakReference<Context> context = ((MovingItem)this.observerList.get(i)).context;
                AttributeSet attrs = ((MovingItem)this.observerList.get(i)).attrs;
                float w = screenWidth;
                float[] fl = new float[]{w/16, w/16*5, w/16*9, w/16*13};
                int r = GameView.random.nextInt(fl.length);
                float y1 = miny - screenHeight/2;
                miny = y1;
                MovingItem s1 = new MovingItem(context.get(), attrs, fl[r], y1, ((MovingItem)this.observerList.get(i)).width, ((MovingItem)this.observerList.get(i)).length, r, screenHeight);
                newStone.registerObservers(s1);
            } else {
                newStone.registerObservers(this.observerList.get(i));
            }
        }
        this.observerList = newStone.observerList;
    }

    // For the drawing we also use observer design pattern
    public void notifyObserversdraw(Canvas c, Paint p) {
        for (Observer s : this.observerList){
            s.itemDraw(c, p);
        }
    }

}