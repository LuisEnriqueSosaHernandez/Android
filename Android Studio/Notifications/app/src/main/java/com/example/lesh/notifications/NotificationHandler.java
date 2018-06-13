package com.example.lesh.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;

public class NotificationHandler extends ContextWrapper {
private NotificationManager manager;
public static final String CHANNEL_HIGH_ID="1";
private final String CHANNEL_HIGH_NAME="HIGH CHANNEL";
public static final String CHANNEL_LOW_ID="2";
private final String CHANNEL_LOW_NAME="LOW CHANNEL";
    public NotificationHandler(Context context) {
        super(context);
        createChannels();
    }

    private void createChannels(){
        if (Build.VERSION.SDK_INT >=26){
            //Creando el high channel
            NotificationChannel highChannel=new NotificationChannel(CHANNEL_HIGH_ID,CHANNEL_HIGH_NAME,NotificationManager.IMPORTANCE_HIGH);
            //Configuracion extra
            highChannel.enableLights(true);
            highChannel.setLightColor(Color.YELLOW);
            highChannel.setShowBadge(true);
            highChannel.enableVibration(true);
            highChannel.setVibrationPattern(new long[]{100,200,300,400,500,400,200,100,400});
            Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            highChannel.setSound(defaultSoundUri,null);
            highChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            NotificationChannel lowChannel=new NotificationChannel(CHANNEL_LOW_ID,CHANNEL_LOW_NAME,NotificationManager.IMPORTANCE_LOW);
            lowChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            getManager().createNotificationChannel(highChannel);
            getManager().createNotificationChannel(lowChannel);
        }
    }

    public NotificationManager getManager() {
        if(manager==null){
            manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            }
            return manager;
    }

    public Notification.Builder createNotification(String title,String message,boolean isHighImportance){
       if(Build.VERSION.SDK_INT>=26){
           if(isHighImportance){
               return this.createNotificationWithChannel(title,message,CHANNEL_HIGH_ID);
           }
           return this.createNotificationWithChannel(title,message,CHANNEL_LOW_ID);
       }
       return this.createNotificationWithoutChannel(title,message);
    }


    private Notification.Builder createNotificationWithChannel(String title, String message, String channelId){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            return new Notification.Builder(getApplicationContext(),channelId).
                    setContentTitle(title).
                    setContentText(message).
                    setColor(getColor(R.color.colorPrimary)).
                    setSmallIcon(android.R.drawable.stat_notify_chat).
                    setAutoCancel(true);

        }
        return null;
    }

    private Notification.Builder createNotificationWithoutChannel(String title,String message){
            return new Notification.Builder(getApplicationContext()).setContentTitle(title).
                    setContentText(message).setSmallIcon(android.R.drawable.stat_notify_chat).
                    setAutoCancel(true);
    }
}

