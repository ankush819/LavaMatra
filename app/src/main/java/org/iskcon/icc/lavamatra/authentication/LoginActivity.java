package org.iskcon.icc.lavamatra.authentication;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.iskcon.icc.lavamatra.R;
import org.iskcon.icc.lavamatra.categories.DisplayCategoriesActivity;
import org.iskcon.icc.lavamatra.util.LogHelper;

/**
 * Created by Ankush on 15-06-2017.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private FirebaseAuth firebaseAuth;
    private EditText inputFullName, inputInitiatedName, inputEmail, inputPassword, inputPhone, inputPlace;
    private String fullName, initiatedName, email, password, phone, place;
    private Button registerButton, resetPasswordButton, loginButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            //TODO : Start next activity right away
            startActivity(new Intent(LoginActivity.this, DisplayCategoriesActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login_user);

        inputEmail = (EditText) findViewById(R.id.loginEmailBox);
        inputPassword = (EditText) findViewById(R.id.loginPasswordBox);

        registerButton = (Button) findViewById(R.id.goToRegisterButton);
        resetPasswordButton = (Button) findViewById(R.id.goToResetButton);
        loginButton = (Button) findViewById(R.id.loginButton);

        firebaseAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterUserActivity.class));
            }
        });

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }

        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = inputEmail.getText().toString().trim();
                password = inputPassword.getText().toString().trim();

                if(!sanitizeInput()) return;

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Could not login" + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(LoginActivity.this, DisplayCategoriesActivity.class);
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
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getApplicationContext(), "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return false;
        }
        LogHelper.log(TAG, "debug", "The user name is " + fullName);
        return true;
    }
}

