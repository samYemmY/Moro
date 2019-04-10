package io.samyemmy.github;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver
{
    private static final String TAG = "MyReceiver";
    public static final String ACTION_TIMEMANAGER_EXECUTE = "timemanager.execute";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (!intent.getAction().equals(ACTION_TIMEMANAGER_EXECUTE)) return;

        Log.d(TAG, "onReceive()");
//        UpdateManager manager = new UpdateManager(context.getFilesDir());
//        manager.background();
    }
}
