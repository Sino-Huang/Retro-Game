package anu.comp2100.teamgg.god_of_hand;

import android.graphics.Canvas;
import android.graphics.Paint;
/**
 * @author COMP2100 TeamGG
 *  The observer is used for implementing Observer Desing Pattern
 */
public interface Observer {
    void itemDraw(Canvas c, Paint p);

    void run();
}
