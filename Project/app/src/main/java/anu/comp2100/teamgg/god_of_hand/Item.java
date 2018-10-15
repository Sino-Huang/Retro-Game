package anu.comp2100.teamgg.god_of_hand;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Item extends View {
    public float x;
    public float y;
    public float width;
    public float length;

    public Item(Context context, @Nullable AttributeSet attrs, float x, float y, float width, float length){
        super(context,attrs);
        this.x = x;
        this.y = y;
        this.width = width;
        this.length = length;
    }
    public void run(){};
    public void itemDraw(Canvas canvas, Paint paint){};


}
