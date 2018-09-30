package anu.comp2100.teamgg.god_of_hand;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Stone extends Sprite {

    Bitmap stoneImage;

    public Stone(Context context, int i){
        if (i == 1){
            this.stoneImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.Stone1);
        }else if (i == 2){
            this.stoneImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.Stone2);
        }else if (i == 3){
            this.stoneImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.Stone3);
        }else if (i == 4){
            this.stoneImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.Stone4);
        }else if (i == 5){
            this.stoneImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.Stone5);
        }
    }

    public Stone(float x, float y, float width, float length){
        this.item = new Item(x,y,width,length);
    }

    @Override
    public void run() {

    }

    @Override
    public void ItemDraw(Canvas c, Paint p) {
        int h = c.getHeight();
        int w = c.getWidth();

        int width = w/8;
        int height = h/16;

        c.drawBitmap(Bitmap.createScaledBitmap(this.stoneImage, width, height, true), this.item.X, this.item.Y, p);

    }
}
