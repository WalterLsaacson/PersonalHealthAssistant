package com.guanyin.sardar.pha.alert.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.guanyin.sardar.pha.R;


public class MusicService extends Service {

    MediaPlayer mp;
    public static Intent newIntent(Context packageContext){
        return new Intent(packageContext,MusicService.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp= new MediaPlayer();
        mp=MediaPlayer.create(MusicService.this, R.raw.music);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        mp.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
//        mp.stop();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}