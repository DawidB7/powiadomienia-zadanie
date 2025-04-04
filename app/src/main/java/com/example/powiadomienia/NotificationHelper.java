package com.example.powiadomienia;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;


public class NotificationHelper {

        private static final String CHANNEL_ID = "default_channel";
        private static final String CHANNEL_NAME = "Kanał powiadomień";


        public static void sendNotification(int NOTIFICATION_ID, AppCompatActivity activity, String title, String message, int styleType, int largeIconResID) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 100);
                    return;
                }
                };
                NotificationManager notificationManager = (NotificationManager)
                        activity.getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
                    notificationManager.createNotificationChannel(channel);
                }

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(activity, CHANNEL_ID)
                                .setSmallIcon(R.drawable.f450216324990b23295ee972465f1cfc)
                                .setContentTitle(title)
                                .setContentText(message)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setAutoCancel(true);


            switch (styleType){
                case 1:
                    builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
                    break;
                case 2:
                    if (largeIconResID != 0){
                        Bitmap largeIcon = BitmapFactory.decodeResource(activity.getResources(), largeIconResID);
                        builder.setLargeIcon(largeIcon);
                        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(largeIcon).setBigContentTitle(title));
                    }
                    break;
                case 3:
                    NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                    inboxStyle.addLine(message);
                    inboxStyle.addLine("Dodatkowa linia tekstu");
                    builder.setStyle(inboxStyle);
                    break;
            }
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
}
