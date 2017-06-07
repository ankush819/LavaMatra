package org.iskcon.icc.lavamatra.service;

import android.text.TextUtils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.iskcon.icc.lavamatra.Model.User;
import org.iskcon.icc.lavamatra.util.LogHelper;

/**
 * Created by Ankush on 07-06-2017.
 */

public class FirebaseDatabaseUpdate {

    private static final String TAG = FirebaseDatabaseUpdate.class.getSimpleName();

    private FirebaseDatabase firebaseInstance;
    private DatabaseReference firebaseDatabase;
    private String userId;

    public void writeNewUser(String fullName, String initiatedName, String email, String place, String phone) {
        LogHelper.log(TAG, "debug", "Writing new user info to Firebase");

        firebaseInstance = FirebaseDatabase.getInstance();
        firebaseDatabase = firebaseInstance.getReference("users"); //TODO : Make it a static final class variable

        if (TextUtils.isEmpty(userId)) {
            createUser(fullName, initiatedName, email, place, phone);
        } else {
            updateUser(fullName, initiatedName, email, place, phone);
        }
    }

    private void createUser(String fullName, String initiatedName, String email, String place, String phone) {
        LogHelper.log(TAG, "debug", "Had to create user in Firebase");
        if (TextUtils.isEmpty(userId)) {
            userId = firebaseDatabase.push().getKey();
        }

        User user = new User(fullName, initiatedName, email, place, phone);
        firebaseDatabase.child(userId).setValue(user);
    }

    private void updateUser(String fullName, String initiatedName, String email, String place, String phone) {
        LogHelper.log(TAG, "debug", "Updating user for userId " + userId);
        firebaseDatabase.child(userId).child("fullName").setValue(fullName);
        firebaseDatabase.child(userId).child("initiatedName").setValue(initiatedName);
        firebaseDatabase.child(userId).child("email").setValue(email);
        firebaseDatabase.child(userId).child("place").setValue(place);
        firebaseDatabase.child(userId).child("phone").setValue(phone);
    }
}
