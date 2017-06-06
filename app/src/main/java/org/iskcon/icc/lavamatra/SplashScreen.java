package org.iskcon.icc.lavamatra;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.iskcon.icc.lavamatra.util.LogHelper;

/**
 * Created by Ankush on 06-06-2017.
 */

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private static final String TAG = SplashScreen.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        LogHelper.log(TAG, "debug", "onCreate() called");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //We are keeping it simple for now and calling the main activity after splash
                //TODO : Add code to see whether the user is using the app for the first time and based on it call the registration class or the main activity
                Intent homeIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(homeIntent);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
