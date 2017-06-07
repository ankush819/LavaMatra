package org.iskcon.icc.lavamatra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.iskcon.icc.lavamatra.util.Constants;
import org.iskcon.icc.lavamatra.util.LogHelper;

/**
 * Created by Ankush on 06-06-2017.
 */

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private static final String TAG = SplashScreen.class.getSimpleName();
    private SharedPreferences sharedPreferences;
    private Intent intent;
    private String registrationSharedPrefName = Constants.REGISTRATION_SHARED_PREF_NAME;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogHelper.log(TAG, "debug", "onCreate() called");

        setContentView(R.layout.activity_splash);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean registrationComplete = sharedPreferences.getBoolean(registrationSharedPrefName, false);

        if (registrationComplete == true) {
            intent = new Intent(SplashScreen.this, MainActivity.class);
            LogHelper.log(TAG, "info", "User already registered");
        } else {
            intent = new Intent(SplashScreen.this, RegisterUser.class);
            LogHelper.log(TAG, "info", "Proceeding for user registration");
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO : Add code to see whether the user is using the app for the first time and based on it call the registration class or the main activity
                //TODO : Back button during splash works BUT the splash timeout is still getting executed. FIX IT.
                //TODO : I press back from MainActivity and come back again, I get welcomed by the Splash and register screen. FIX IT.
                //Even RISE app has the same problem as the above to do. Maybe it can be ignored for now.
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
