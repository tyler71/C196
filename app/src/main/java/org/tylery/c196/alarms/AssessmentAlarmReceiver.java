package org.tylery.c196.alarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.tylery.c196.R;

public class AssessmentAlarmReceiver extends BroadcastReceiver {
    public static final String ALARM_NOTIFICATION_TITLE =
            "org.tylery.c196.alarms.ALARM_NOTIFICATION_TITLE";
    public static final String ALARM_NOTIFICATION_TEXT =
            "org.tylery.c196.alarms.ALARM_NOTIFICATION_TEXT";

    public static final String ASSESSMENT_CHANNEL_ID_ALARMS = "CHANNEL_ID_COURSE_ALARMS";
    public static final int NOTIFY_ID_ASSESSMENT_ALARM = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context.getApplicationContext());

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), ASSESSMENT_CHANNEL_ID_ALARMS)
                .setSmallIcon(R.drawable.ic_add_white_24dp)
                .setContentTitle(intent.getStringExtra(ALARM_NOTIFICATION_TITLE))
                .setContentText(intent.getStringExtra(ALARM_NOTIFICATION_TITLE + "\n" + ALARM_NOTIFICATION_TEXT))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(NOTIFY_ID_ASSESSMENT_ALARM, builder.build());
    }
}