package io.samyemmy.github;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver
{
    private static final String TAG = "MyReceiver";
    private static final int NOTIFICATION_ID = 420;
    public static final String ACTION_TIMEMANAGER_EXECUTE = "timemanager.execute";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (!intent.getAction().equals(ACTION_TIMEMANAGER_EXECUTE)) return;

        Log.d(TAG, "onReceive()");
        UpdateManager manager = new UpdateManager(context.getFilesDir());
        if (manager.background())
        {
            sendNotification(context);
        }
    }

    public void sendNotification(Context context)
    {
        Intent intent = new Intent(context, AndroidLauncher.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, MyGame.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Moroidesu")
                .setContentText("Moroidesu needs your Attention!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
