package com.example.pezyandroid.louise.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.pezyandroid.R;


public class AppNotification {
    private static final AppNotification ourInstance = new AppNotification();
    private static Context fContext;

    private Notification fNotif;
    private NotificationCompat.Builder fBuilder;

    private String fChannelID;
    private String fTitle;
    private String fContent;
    private int fLogo;
    private int fRandomID;
    private String fTag;


    public static AppNotification init(Context context) {
        fContext = context;
        return ourInstance;
    }

    public AppNotification() {
    }


    public AppNotification make(String tag, int randomID, String channelID, String title, String content, int logo) {
        fChannelID = channelID;
        fTitle = title;
        fContent = content;
        fLogo = logo;
        fTag = tag;
        fRandomID = randomID;

        /**
         * Set notification property
         */
        fBuilder = new NotificationCompat.Builder(fContext.getApplicationContext(), fChannelID)
                .setSmallIcon(fLogo)
                .setContentTitle(fTitle)
                .setContentText(fContent)
                .setChannelId(fChannelID);

        return ourInstance;
    }

    public AppNotification setContentIntent(PendingIntent pIntent){
        if(fBuilder != null){
            fBuilder.setContentIntent(pIntent);
        }
        return ourInstance;
    }

    public AppNotification setPriority(int priority){
        if(fBuilder != null){
            // NotificationCompat.PRIORITY_DEFAULT
            fBuilder.setPriority(priority);
        }
        return ourInstance;
    }

    public AppNotification setAutoCancel(boolean autocancel){
        if(fBuilder != null){
            fBuilder.setAutoCancel(autocancel);
        }
        return ourInstance;
    }

    public void build(){
        if(fChannelID != null){
            fNotif = fBuilder.build();

            /**
             * Create NotificationManager
             */
            NotificationManager notifManager = (NotificationManager) fContext.getSystemService(fContext.NOTIFICATION_SERVICE);


            /**
             * Check android version if Build version is over than or equal to MARSHMELLOW, Then create channel id.
             */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(fChannelID,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_DEFAULT);
                notifManager.createNotificationChannel(channel);
            }

            /**
             * Trigger the notifications.
             */
            notifManager.notify(fTag, fRandomID, fNotif);
        }else{
            Log.wtf(fContext.getString(R.string.log_debug), "Cannot build notif because channel id was null!");
        }
    }
}
