package org.iskcon.icc.lavamatra.Model;

/**
 * Created by Ankush on 07-06-2017.
 */

public class User {

    public String fullName;
    public String initiatedName;
    public String email;
    public String place;
    public String phoneNumber;

    public User() {

    }

    public User(String fullName, String initiatedName, String email, String place, String phoneNumber) {
        this.fullName = fullName;
        this.initiatedName = initiatedName;
        this.email = email;
        this.place = place;
        this.phoneNumber = phoneNumber;
    }
}
