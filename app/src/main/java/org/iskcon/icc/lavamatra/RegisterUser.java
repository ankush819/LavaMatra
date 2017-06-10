package org.iskcon.icc.lavamatra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.iskcon.icc.lavamatra.categories.DisplayCategoriesActivity;
import org.iskcon.icc.lavamatra.service.FirebaseDatabaseUpdate;
import org.iskcon.icc.lavamatra.util.Constants;
import org.iskcon.icc.lavamatra.util.LogHelper;

/**
 * Created by Ankush on 06-06-2017.
 */

public class RegisterUser extends AppCompatActivity {

    private static final String TAG = RegisterUser.class.getSimpleName();
    private SharedPreferences sharedPreferences;
    private boolean completed;
    private EditText inputFullName, inputInitiatedName, inputEmail, inputPhone, inputPlace;
    private String fullName, initiatedName, email, phone, place;
    private Button registerButton;
    private Intent intent;
    private String registrationSharedPrefName = Constants.REGISTRATION_SHARED_PREF_NAME;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogHelper.log(TAG, "debug", "onCreate() called");

        setContentView(R.layout.activity_register_user);

        inputFullName = (EditText) findViewById(R.id.editFullName);
        inputInitiatedName = (EditText) findViewById(R.id.editInitiatedName);
        inputEmail = (EditText) findViewById(R.id.editEmail);
        inputPhone = (EditText) findViewById(R.id.editPhone);
        inputPlace = (EditText) findViewById(R.id.editPlace);

        registerButton = (Button) findViewById(R.id.registerButton);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RegisterUser.this);
        completed = sharedPreferences.getBoolean(registrationSharedPrefName, false);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullName = inputFullName.getText().toString().trim();
                initiatedName = inputInitiatedName.getText().toString().trim();
                email = inputEmail.getText().toString().trim();
                phone = inputPhone.getText().toString().trim();
                place = inputPlace.getText().toString().trim();

                if(!sanitizeInput()) return;

                if (!completed) {
                    LogHelper.log(TAG, "debug", "User has not registered. Updating firebase DB with user info.");
                    FirebaseDatabaseUpdate fbUpdate = new FirebaseDatabaseUpdate();
                    fbUpdate.writeNewUser(fullName, initiatedName, email, place, phone); //TODO : Update only if shared preferences is not set
                    updateSharedPreferences();
                }
                LogHelper.log(TAG, "debug", "Starting activity MainActivity from RegisterUser class");
                intent = new Intent(RegisterUser.this, DisplayCategoriesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateSharedPreferences() {
        LogHelper.log(TAG, "debug", "Updating sharedPreference to mark user registration complete");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(registrationSharedPrefName, true);
        editor.commit();
    }

    private boolean sanitizeInput() {
        //TODO : Do all sanity checks of the form data here
        if (TextUtils.isEmpty(fullName)) {
            Toast.makeText(getApplicationContext(), "Please enter your full name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(getApplicationContext(), "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(place)) {
            Toast.makeText(getApplicationContext(), "Please enter your place", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getApplicationContext(), "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return false;
        }
        LogHelper.log(TAG, "debug", "The user name is " + fullName);
        return true;
    }
}
