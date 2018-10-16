package anu.comp2100.teamgg.god_of_hand;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {
    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.bgm);
        player.setLooping(true);
        player.setVolume(100, 100);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.bgm);
            player.setLooping(true);
            player.setVolume(100, 100);
        }
        player.start();
        Log.w("Music", "Music start");
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.w("Music", "Music End");
        player.stop();
        player.reset();
        player.release();
        player = null;
        super.onDestroy();
    }
}
