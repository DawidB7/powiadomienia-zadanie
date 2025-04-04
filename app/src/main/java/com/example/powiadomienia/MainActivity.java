package com.example.powiadomienia;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.BitmapCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "my_channel_id";
    private static int ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        Button button = findViewById(R.id.bigText);
        button.setOnClickListener(v-> {
            NotificationHelper.sendNotification(ID, this, "Big Text", "Powiadomienie Big Text", 0, 0);
                ID++;
            }
        );
        Button button1 = findViewById(R.id.bigPicture);
        button1.setOnClickListener(v-> {
                    NotificationHelper.sendNotification(ID, this, "Big Picture", "Powiadomienie Big Picture", 1, 0);
                    ID++;
                }
        );
        Button button2 = findViewById(R.id.inbox);
        button2.setOnClickListener(v-> {
                    NotificationHelper.sendNotification(ID, this, "Inbox", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vulputate turpis risus, ac molestie nisi tincidunt eu. Suspendisse vitae ullamcorper quam. Donec in odio mattis elit vulputate pharetra auctor et erat. Vestibulum a justo aliquet, eleifend ipsum et, hendrerit mi. Nullam at tristique dolor. Curabitur eu semper nisl, gravida commodo ligula. Cras ornare, neque et sagittis cursus, metus ex tristique felis, ac faucibus tortor lorem non nunc. Vivamus venenatis eros vitae elementum varius. Aliquam vitae odio velit. Aenean rutrum urna in pulvinar posuere. Nullam imperdiet cursus quam, sit amet volutpat urna tincidunt vitae. Nulla facilisi. Nunc vitae tellus ex. Nullam id quam eros. Praesent aliquam faucibus mauris non euismod. Aliquam euismod nec turpis ac mollis.", 1, R.drawable.f450216324990b23295ee972465f1cfc);
                    ID++;
                }
        );

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Kanał Powiadomień";
            String description = "Opis kanału powiadomień";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
