package com.mapped;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Pegasus on 7/16/16.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentOne();

            case 1:
                return new FragmentTwo();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
