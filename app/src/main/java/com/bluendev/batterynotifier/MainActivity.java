package com.bluendev.batterynotifier;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
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
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ImageView batLogo, blueNLogo;
    Animation anim;
    ConstraintLayout entrance, main;
    TextView batteryLevel, chargingStatus;

    IntentFilter iFilter;
    Intent batteryStatus;
    int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar= findViewById(R.id.my_toolbar);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);


        iFilter= new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryStatus= getApplicationContext().registerReceiver(null,iFilter);

        status= batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS,-1);

        boolean charging= status== BatteryManager.BATTERY_STATUS_CHARGING;
        boolean discharging= status==  BatteryManager.BATTERY_STATUS_DISCHARGING;
        boolean batFull= status== BatteryManager.BATTERY_STATUS_FULL;




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



        batteryLevel= findViewById(R.id.batteryLevel);
        chargingStatus= findViewById(R.id.chargingStatus);

        if(charging){
            chargingStatus.append("Charging");
        }else if(discharging){
            chargingStatus.append("Discharging");
        }else if(batFull){
            chargingStatus.append("Full");
        }


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
    public void onBackPressed() { super.onBackPressed();
    }
}
