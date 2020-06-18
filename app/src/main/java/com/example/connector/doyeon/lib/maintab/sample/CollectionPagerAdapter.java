package com.example.connector.doyeon.lib.maintab.sample;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class CollectionPagerAdapter extends FragmentStatePagerAdapter {

    public CollectionPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ObjectFragment();
        Bundle args = new Bundle();
       // args.putInt(ObjectFragment.ARG_OBJECT, );
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
