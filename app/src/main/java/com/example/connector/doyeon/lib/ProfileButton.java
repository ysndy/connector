package com.example.connector.doyeon.lib;

import android.content.Context;
import android.view.Gravity;

import com.example.connector.doyeon.objects.Profile;

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
