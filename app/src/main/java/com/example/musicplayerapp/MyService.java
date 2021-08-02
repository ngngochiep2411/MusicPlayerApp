package com.example.musicplayerapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.musicplayerapp.Model.BaiHat;

import static com.example.musicplayerapp.CreateNotification.CHANEL_ID;

public class MyService extends Service {
    String i1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle=intent.getExtras();
        //if(bundle!=null){
            //BaiHat baiHat = (BaiHat) bundle.get("send_to_service");
           i1=intent.getStringExtra("123");
//            if(baiHat!=null){
               sendNotification(i1);
//            }

       // }
        return START_NOT_STICKY;
    }

    public void sendNotification(String i) {
//        Intent intent=new Intent(this,DetailActivity.class);
//        PendingIntent pendingIntent=
//                PendingIntent.getActivity(this,0,intent,0);
        Intent switchIntent = new Intent("com.example.app.ACTION_PLAY");
        PendingIntent pendingSwitchIntent = PendingIntent.getBroadcast(this, 100, switchIntent, 0);

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),baiHat.getAvtBaiHat());
//
  RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.custom_notification);
       remoteViews.setTextViewText(R.id.tv_title_song,i);
//       remoteViews.setTextViewText(R.id.tv_single_song,baiHat.getTenCaSi());
//       remoteViews.setImageViewBitmap(R.id.imgSong,bitmap);

        Notification notification= new NotificationCompat.Builder(this,CHANEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_music_note_24)
                .setContentIntent(pendingSwitchIntent)
                .setCustomContentView(remoteViews)
                .setContentText(i)
                .setSound(null)
                .build();

        startForeground(1,notification);

    }



}
