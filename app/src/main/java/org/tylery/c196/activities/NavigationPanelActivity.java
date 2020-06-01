package org.tylery.c196.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import org.tylery.c196.R;

import static org.tylery.c196.utils.AlarmReceiver.CHANNEL_ID_ALARMS;

public class NavigationPanelActivity extends AppCompatActivity {
    private Button viewTermListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_panel);

//        Android 8+ requires notification channels
        createNotificationChannels();

        viewTermListBtn = findViewById(R.id.btn_view_term_list);
        viewTermListBtn.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), TermListActivity.class);
            v.getContext().startActivity(intent);
        });

    }


    private void createNotificationChannels() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Course Alarms";
            String description = "When the course starts and ends";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_ALARMS, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
