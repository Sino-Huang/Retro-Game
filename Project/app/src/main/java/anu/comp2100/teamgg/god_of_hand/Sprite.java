package anu.comp2100.teamgg.god_of_hand;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Sprite {
    Item item;

    public abstract void run();

    public abstract void ItemDraw(Canvas c, Paint p);

}
