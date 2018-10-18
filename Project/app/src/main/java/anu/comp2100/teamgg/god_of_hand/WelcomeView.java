package anu.comp2100.teamgg.god_of_hand;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import org.w3c.dom.Node;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author COMP2100 TeamGG
 * This WelcomeView is the initial view of the app and contains the scoreboard information.
 *  Facade design pattern is used to store and load data on the scoreboard
 */
public class WelcomeView extends View {

    String heroName = "Guest";
    public int heroScore = 0;
    WelcomeActivity activity;
    Canvas canvas = null;
    Paint p;

    public WelcomeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // load the scoreboard information
        activity = (WelcomeActivity) getContext();
        p = new Paint();
        p.setTextSize(40);
        p.setTextAlign(Paint.Align.CENTER);
        String dir = activity.getExternalFilesDir(null).getAbsolutePath();
        File file = new File(dir + "/scoreboard.xml");
        if (file.exists()) {
            Log.w("FILE", "File exist");
            FileInputStream fos = null;
            try {
                fos = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            LoadStoreFacade loadf = LoadStoreFacade.createLoad(fos);
            Node n = loadf.load();
            heroName = n.getNodeName();
            heroScore = Integer.parseInt(n.getTextContent());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.canvas == null) {
            this.canvas = canvas;
        }
        WelcomeActivity activity = (WelcomeActivity) getContext();
         activity.height = canvas.getHeight();
         activity.width = canvas.getWidth();

        // draw scoreboard
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.score_board);
        Bitmap resizebm = Bitmap.createScaledBitmap(bitmap, (int) (canvas.getWidth() * 0.5), (int)(canvas.getHeight() * 0.5), true);
        canvas.drawBitmap(resizebm, (float)(canvas.getWidth() * 0.25), (float)(canvas.getHeight() * 0.4), p);
        canvas.drawText("Hero Board", canvas.getWidth()/2, (float) (canvas.getHeight() * 0.5), p);
        canvas.drawText(String.valueOf(heroName), canvas.getWidth()/2, (float) (canvas.getHeight() * 0.6), p);
        canvas.drawText(String.valueOf(heroScore), canvas.getWidth()/2, (float) (canvas.getHeight() * 0.7), p);

    }

    public void updateScore(String username, int finalscore) {
        heroName = username;
        heroScore = finalscore;

        // save the score
        String dir = activity.getExternalFilesDir(null).getAbsolutePath();
        File file = new File(dir + "/scoreboard.xml");
        Log.w("File dir", file.getAbsolutePath());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        LoadStoreFacade savef = LoadStoreFacade.createSave(fos);
        savef.addElement(username, finalscore);
        savef.save();
    }

}
