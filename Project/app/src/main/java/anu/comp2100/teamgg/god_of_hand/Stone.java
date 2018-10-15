package anu.comp2100.teamgg.god_of_hand;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.Random;

public class Stone extends Item {

    Bitmap stoneImage;
    Context context;
    @Nullable AttributeSet attrs;
    public Stone(Context context, @Nullable AttributeSet attrs,float x, float y, float width, float length){
        super(context, attrs, x, y, width, length);
        this.context = context;
        this.attrs = attrs;
        Random random = new Random();
        int i =random.nextInt(13);
        switch (i){
            case 0: case 1:this.stoneImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.stone1); break;
            case 2: case 3:this.stoneImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.stone2); break;
            case 4: case 5:this.stoneImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.stone3); break;
            case 6: case 7:this.stoneImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.stone4); break;
            case 8: case 9:this.stoneImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.stone5); break;
            case 10: this.stoneImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.diamondegg);break;
            case 11: this.stoneImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.goldenegg);break;
            case 12: this.stoneImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.centuryegg);break;
        }

    }

    @Override
    public void run() {
        this.y += 6;
    }

    @Override
    public void itemDraw(Canvas c, Paint p) {
        c.drawBitmap(Bitmap.createScaledBitmap(this.stoneImage, (int)this.width, (int)this.length, true), this.x, this.y, p);

    }
}
