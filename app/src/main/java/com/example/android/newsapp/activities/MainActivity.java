package com.example.android.newsapp.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.newsapp.R;
import com.example.android.newsapp.adapters.ViewPagerAdapter;
import com.example.android.newsapp.listener.FragmentLifeCycleListener;

public class MainActivity extends AppCompatActivity {
    final static String LOG_TAG = MainActivity.class.getSimpleName();

    private ViewPagerAdapter adapter;

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        int currentPosition = 0;

        @Override
        public void onPageSelected(int newPosition) {
            Log.i("Main", "currentPosition =  " + currentPosition);
            Log.i("Main", "newPosition =  " + newPosition);
            FragmentLifeCycleListener fragmentToHide = (FragmentLifeCycleListener) adapter.getItem(currentPosition);
            fragmentToHide.onPauseTask();

            FragmentLifeCycleListener fragmentToShow = (FragmentLifeCycleListener) adapter.getItem(newPosition);
            fragmentToShow.onResumeTask();

            currentPosition = newPosition;
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {
        }

        public int getCurrentPosition() {
            return currentPosition;
        }
    };
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
        viewHolder.viewPager.setOffscreenPageLimit(adapter.getCount());
        viewHolder.tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        viewHolder.tabLayout.setupWithViewPager(viewHolder.viewPager);

        //viewHolder.viewPager.addOnPageChangeListener(pageChangeListener);


    }


}
