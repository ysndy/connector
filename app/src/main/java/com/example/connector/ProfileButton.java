package com.example.connector;

import android.content.Context;
import android.view.Gravity;
import android.widget.Button;

import com.example.connector.objects.Profile;

public class ProfileButton extends androidx.appcompat.widget.AppCompatButton {

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    private Profile profile;

    public ProfileButton(Context context) {
        super(context);
        setGravity(Gravity.CENTER);
    }


}
