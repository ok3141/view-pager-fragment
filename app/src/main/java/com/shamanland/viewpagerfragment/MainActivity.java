package com.shamanland.viewpagerfragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOnPageChangeListener(new ViewPagerHelper(getSupportFragmentManager(), viewPager));
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }
}
