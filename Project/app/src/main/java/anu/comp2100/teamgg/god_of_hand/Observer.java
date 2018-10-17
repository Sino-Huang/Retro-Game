package anu.comp2100.teamgg.god_of_hand;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface Observer {
    void itemDraw(Canvas c, Paint p);

    void run();
}
