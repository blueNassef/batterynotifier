package com.bluendev.batterynotifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ImageView batLogo, blueNLogo;
    Animation anim;
    ConstraintLayout entrance, main;
    TextView batteryLevel, chargingStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.registerReceiver(batRec, batFil);

        Toolbar myToolbar= findViewById(R.id.my_toolbar);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        batteryLevel= findViewById(R.id.batteryLevel);
        chargingStatus= findViewById(R.id.chargingStatus);


        anim= AnimationUtils.loadAnimation(this,R.anim.enlarge);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                entrance.setVisibility(View.GONE);
                main.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        anim.start();

        batLogo= findViewById(R.id.batLogo);
        blueNLogo= findViewById(R.id.blueNLogo);
        entrance= findViewById(R.id.intrance);
        main= findViewById(R.id.main);

        batLogo.setAnimation(anim);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_rate:

                return true;

            case R.id.action_fullversion:

                return true;

            case R.id.action_exit:
                finish();
            default:

                return super.onOptionsItemSelected(item);

            }
        }

    @Override
    public void onBackPressed() {
    }

    //receive battery level and charging status
    private IntentFilter batFil = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    private BroadcastReceiver batRec = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            batteryLevel.setText("Battery level: "+String.valueOf(level) + "%");
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;
            if (isCharging) {
                chargingStatus.setText("Battery status: "+"Charging");
            } else {
                chargingStatus.setText("Battery status: "+"Discharging");
            }
        }
    };
}
