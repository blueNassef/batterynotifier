package com.bluendev.batterynotifier;


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


public class MainActivity extends AppCompatActivity {

    ImageView batLogo, blueNLogo;
    Animation anim;
    ConstraintLayout entrance, main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar= findViewById(R.id.my_toolbar);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);



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
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_fullversion:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.action_exit:
                finish();
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

            }
        }

    @Override
    public void onBackPressed() { super.onBackPressed();
    }
}
