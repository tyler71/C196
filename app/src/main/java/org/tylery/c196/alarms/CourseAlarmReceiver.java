package org.tylery.c196.alarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.tylery.c196.R;

import static android.content.ContentValues.TAG;


public class CourseAlarmReceiver extends BroadcastReceiver {
    public static final String ALARM_NOTIFICATION_TITLE =
            "org.tylery.c196.utils.ALARM_NOTIFICATION_TITLE";
    public static final String ALARM_NOTIFICATION_TEXT =
            "org.tylery.c196.utils.ALARM_NOTIFICATION_TEXT";

    public static final String CHANNEL_ID_ALARMS = "CHANNEL_ID_COURSE_ALARMS";
    public static final int NOTIFY_ID_COURSE_ALARM = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: AlarmReceiver was run");
        Toast.makeText(context, "alarm received", Toast.LENGTH_SHORT).show();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context.getApplicationContext());

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), CHANNEL_ID_ALARMS)
                .setSmallIcon(R.drawable.ic_add_white_24dp)
                .setContentTitle(intent.getStringExtra(ALARM_NOTIFICATION_TITLE))
                .setContentText(intent.getStringExtra(ALARM_NOTIFICATION_TEXT))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(NOTIFY_ID_COURSE_ALARM, builder.build());
    }
}
