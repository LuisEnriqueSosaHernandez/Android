package com.example.lesh.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

public class NotificationHandler extends ContextWrapper {
private NotificationManager manager;
public static final String CHANNEL_HIGH_ID="1";
private final String CHANNEL_HIGH_NAME="HIGH CHANNEL";
public static final String CHANNEL_LOW_ID="1";
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

            NotificationChannel lowChannel=new NotificationChannel(CHANNEL_LOW_ID,CHANNEL_LOW_NAME,NotificationManager.IMPORTANCE_LOW);

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
}
