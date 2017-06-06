package org.iskcon.icc.lavamatra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.iskcon.icc.lavamatra.util.LogHelper;

/**
 * Created by Ankush on 06-06-2017.
 */

public class RegisterUser extends AppCompatActivity {

    private static final String TAG = RegisterUser.class.getSimpleName();
    private SharedPreferences sharedPreferences;
    private EditText userName;
    private Button registerButton;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogHelper.log(TAG, "debug", "onCreate() called");

        setContentView(R.layout.activity_register_user);

        userName = (EditText) findViewById(R.id.fullName);
        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = userName.getText().toString();
                //TODO : Do all sanity checks of the form data here
                //Dummy for now
                if (TextUtils.isEmpty(fullName)) {
                    fullName = "DUMMY_USER";
                }
                LogHelper.log(TAG, "debug", "The user name is " + fullName);

                //TODO : Send data to google drive
                //TODO : Update sharedPreferences boolean completed

                intent = new Intent(RegisterUser.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
