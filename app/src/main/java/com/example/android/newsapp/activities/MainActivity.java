package com.example.android.newsapp.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.newsapp.R;
import com.example.android.newsapp.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    final static String LOG_TAG = MainActivity.class.getSimpleName();

    private ViewPagerAdapter adapter;


    static class ViewHolder {
        private TabLayout tabLayout;
        private ViewPager viewPager;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(LOG_TAG, "in onCreate");

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        viewHolder.viewPager.setAdapter(adapter);
        viewHolder.viewPager.setOffscreenPageLimit(4);
        viewHolder.viewPager.setCurrentItem(0);

        viewHolder.tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        viewHolder.tabLayout.setupWithViewPager(viewHolder.viewPager);


    }


}
