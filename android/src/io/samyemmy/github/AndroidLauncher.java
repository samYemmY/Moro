package io.samyemmy.github;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication implements Android
{
	public static final int NOTIFICATION_ID = 420;
	PendingIntent pendingIntent;
	AlarmManager alarmManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		Intent intent = new Intent(this, MyReceiver.class);
		intent.setAction(MyReceiver.ACTION_TIMEMANAGER_EXECUTE);
		this.pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

		createNotificationChannel();

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		MyGame myGame = MyGame.getInstance();
		myGame.setAndroid(this);
		initialize(myGame, config);
	}

	private void createNotificationChannel()
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
		{
			int importance = NotificationManager.IMPORTANCE_DEFAULT;
			NotificationChannel channel = new NotificationChannel(MyGame.CHANNEL_ID, "Moroidesu", importance);
			channel.setDescription("Moroidesu Channel");
			channel.setVibrationPattern(new long[]{0, 600, 200, 600});
			NotificationManager manager = getSystemService(NotificationManager.class);
			manager.createNotificationChannel(channel);
		}
	}

	public void sendNotification(String msg)
	{
		sendNotification(this, msg);
	}

	public void sendNotification(Context context, String msg)
	{
		Intent intent = new Intent(context, AndroidLauncher.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(context, MyGame.CHANNEL_ID)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Moroidesu")
				.setContentText(msg)
				.setPriority(NotificationCompat.PRIORITY_DEFAULT)
				.setContentIntent(pendingIntent)
				.setAutoCancel(true);

		NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
		notificationManager.notify(NOTIFICATION_ID, builder.build());
	}


	@Override
	protected void onPause() {
		super.onPause();
		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 1000*60, this.pendingIntent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		alarmManager.cancel(this.pendingIntent);
	}

	public void toast(final String msg)
	{
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(AndroidLauncher.this, msg, Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void longToast(final String msg)
	{
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(AndroidLauncher.this, msg, Toast.LENGTH_LONG).show();
			}
		});
	}
}


