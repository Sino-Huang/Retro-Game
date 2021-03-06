package anu.comp2100.teamgg.god_of_hand;
/**
 * @author COMP2100 TeamGG
 *  Item is a basic class that be extended to reduce redundant code.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.lang.ref.WeakReference;

public class Item extends View {
    public float x;
    public float y;
    public float width;
    public float length;
    public int col;
    WeakReference<Context> context;

    public Item(Context context, @Nullable AttributeSet attrs, float x, float y, float width, float length, int col){
        super(context,attrs);
        if (context != null) {
            this.context = new WeakReference<>(context.getApplicationContext());
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.length = length;
        this.col = col;
    }
    public void run(){};
    public void itemDraw(Canvas canvas, Paint paint){};


}
