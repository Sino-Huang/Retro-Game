package anu.comp2100.teamgg.god_of_hand;
/**
 * @author COMP2100 TeamGG
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
    int id;// 0 for stones, 1 for diamondegg item, 2 for goldenegg item, 3 for centuryegg item
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
    public void run() {
        this.y += screenHeight/50;
    }// match the screen size

    @Override
    public void itemDraw(Canvas c, Paint p) {
        c.drawBitmap(Bitmap.createScaledBitmap(this.stoneImage, (int)this.width, (int)this.length, true), this.x, this.y, p);
    }
}
