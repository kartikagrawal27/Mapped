package com.mapped;

import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import github.chenupt.springindicator.SpringIndicator;

public class ViewPagerActivity extends FragmentActivity{

    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        viewpager = (ViewPager) findViewById(R.id.pager);
        viewpager.setOffscreenPageLimit(2);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(pagerAdapter);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.myIndicator);
        springIndicator.setViewPager(viewpager);



    }
}
