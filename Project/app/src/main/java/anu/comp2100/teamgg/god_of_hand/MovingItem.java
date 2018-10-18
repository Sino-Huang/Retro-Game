package anu.comp2100.teamgg.god_of_hand;
/**
 * @author COMP2100 TeamGG
 * MovingItem is the item that going cross the screen. And there are totally 4 types of them and each has a particular effect when touching the egg:
 * stones can terminate the game excepte for when the egg is in invincible status; diamond egg turn the egg to invincible status;
 *  golden egg can trible the score; and century egg will reverse the operations of touching the egg..
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.lang.ref.WeakReference;

public class MovingItem extends Item implements Observer{

    Bitmap stoneImage;
    float screenHeight;
    // id is used for identifying the MovingItem:
    // 0 for stones, 1 for diamondegg, 2 for goldenegg, and 3 for centuryegg.
    int id;
    WeakReference<Context> wr;
    @Nullable AttributeSet attrs;
    public MovingItem(Context context, @Nullable AttributeSet attrs, float x, float y, float width, float length, int col, float screenHeight){
        super(context, attrs, x, y, width, length, col);
        this.attrs = attrs;
        this.wr = new WeakReference<>(context);
        this.screenHeight = screenHeight;
        int i = GameView.random.nextInt(13);
        if (wr.get() != null) {
            switch (i){
                case 0: case 1:this.stoneImage = BitmapFactory.decodeResource(wr.get().getResources(), R.drawable.stone1); id = 0;break;
                case 2: case 3:this.stoneImage = BitmapFactory.decodeResource(wr.get().getResources(), R.drawable.stone2); id = 0;break;
                case 4: case 5:this.stoneImage = BitmapFactory.decodeResource(wr.get().getResources(), R.drawable.stone3); id = 0;break;
                case 6: case 7:this.stoneImage = BitmapFactory.decodeResource(wr.get().getResources(), R.drawable.stone4); id = 0;break;
                case 8: case 9:this.stoneImage = BitmapFactory.decodeResource(wr.get().getResources(), R.drawable.stone5); id = 0;break;
                case 10: this.stoneImage = BitmapFactory.decodeResource(wr.get().getResources(), R.drawable.diamondegg);
                    id = 1;break;
                case 11: this.stoneImage = BitmapFactory.decodeResource(wr.get().getResources(), R.drawable.goldenegg);
                    id = 2;break;
                case 12: this.stoneImage = BitmapFactory.decodeResource(wr.get().getResources(), R.drawable.centuryegg);
                    id = 3;break;
            }
        }
    }

    @Override
    // match the screen size
    public void run() {
        this.y += screenHeight/50;
    }

    @Override
    public void itemDraw(Canvas c, Paint p) {
        c.drawBitmap(Bitmap.createScaledBitmap(this.stoneImage, (int)this.width, (int)this.length, true), this.x, this.y, p);
    }
}
