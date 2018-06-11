package com.bluendev.batterynotifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

public class powerConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        boolean isCharging= status== BatteryManager.BATTERY_STATUS_CHARGING ||
                status== BatteryManager.BATTERY_STATUS_FULL;


        Intent i= new Intent(context,MainActivity.class);

        i.putExtra("batstat",isCharging);
        context.startActivity(i);
    }
}
