package org.iskcon.icc.lavamatra.navigation;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Ankush on 14-09-2017.
 */

public class Signout {

    private static FirebaseAuth auth;

    public static void signOut() {
        auth = FirebaseAuth.getInstance();
        auth.signOut();
    }
}
