package io.samyemmy.github;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication implements Android{
    private static final String TAG = "AndroidLauncher";
	PendingIntent pendingIntent;
	AlarmManager alarmManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate()");
		this.alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		Intent intent = new Intent(this, MyReceiver.class);
		intent.setAction(MyReceiver.ACTION_TIMEMANAGER_EXECUTE);
		this.pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		initialize(MyGame.getInstance(this), config);
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause()");
		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 1000*60, this.pendingIntent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume()");
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
}


