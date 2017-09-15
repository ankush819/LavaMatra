package org.iskcon.icc.lavamatra.authentication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.iskcon.icc.lavamatra.R;
import org.iskcon.icc.lavamatra.RegisterUser;
import org.iskcon.icc.lavamatra.categories.DisplayCategoriesActivity;
import org.iskcon.icc.lavamatra.service.FirebaseDatabaseUpdate;
import org.iskcon.icc.lavamatra.util.Constants;
import org.iskcon.icc.lavamatra.util.LogHelper;

/**
 * Created by Ankush on 15-06-2017.
 */

public class RegisterUserActivity extends AppCompatActivity {

    private static final String TAG = RegisterUserActivity.class.getSimpleName();
    private SharedPreferences sharedPreferences;
    private boolean completed;
    private EditText inputFullName, inputEmail, inputPassword, inputPhone, inputPlace;
    private Spinner inputInitiatedName;
    private String fullName, initiatedName, email, password, phone, place;
    private Button registerButton, resetPasswordButton, loginButton;
    private Intent intent;
    private String registrationSharedPrefName = Constants.REGISTRATION_SHARED_PREF_NAME;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        firebaseAuth = FirebaseAuth.getInstance();

        inputFullName = (EditText) findViewById(R.id.editFullName);
        inputInitiatedName = (Spinner) findViewById(R.id.editInitiatedName);
        inputEmail = (EditText) findViewById(R.id.editEmail);
        inputPassword = (EditText) findViewById(R.id.editPassword);
        inputPhone = (EditText) findViewById(R.id.editPhone);
        inputPlace = (EditText) findViewById(R.id.editPlace);

        registerButton = (Button) findViewById(R.id.registerButton);
        //resetPasswordButton = (Button) findViewById(R.id.resetPasswordButton);
        loginButton = (Button) findViewById(R.id.goToLoginButton);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RegisterUserActivity.this);
        completed = sharedPreferences.getBoolean(registrationSharedPrefName, false);
        //TODO : Uncomment and see what is going wrong here


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : Go to SignIn option
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullName = inputFullName.getText().toString().trim();
                initiatedName = String.valueOf(inputInitiatedName.getSelectedItem());
                email = inputEmail.getText().toString().trim();
                password = inputPassword.getText().toString().trim();
                phone = inputPhone.getText().toString().trim();
                place = inputPlace.getText().toString().trim();

                if(!sanitizeInput()) return;

                //TODO : Add progress bar as given in the androidHive document

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterUserActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Authentication failed" + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    //TODO : Start the right activity
                                    LogHelper.log(TAG, "debug", "Registration success!!");
                                    FirebaseDatabaseUpdate fbUpdate = new FirebaseDatabaseUpdate();
                                    fbUpdate.writeNewUser(fullName, initiatedName, email, place, phone);
                                    intent = new Intent(RegisterUserActivity.this, DisplayCategoriesActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
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
