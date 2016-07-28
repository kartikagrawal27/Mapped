package com.mapped;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.List;

/**
 * Created by Pegasus on 7/5/16.
 */
public class UserInfo {

    private String email;
    private String first_name;
    private String last_name;
    private String user_type;
    private List<String> subscribed_events;

    public String getUniversity() {
        return university;
    }

    private String university;


    public UserInfo() {

    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUser_type() {
        return user_type;
    }

    public List<String> getSubscribed_events() {
        return subscribed_events;
    }
}
