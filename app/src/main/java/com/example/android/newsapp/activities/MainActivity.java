package com.example.android.newsapp.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.newsapp.R;
import com.example.android.newsapp.Utils.DefaultParameter;
import com.example.android.newsapp.Utils.QueryUtils;
import com.example.android.newsapp.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    final static String LOG_TAG = MainActivity.class.getSimpleName();

    static class ViewHolder {
        private TabLayout tabLayout;
        private ViewPager viewPager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewHolder.viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), this));

        viewHolder.tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        viewHolder.tabLayout.setupWithViewPager(viewHolder.viewPager);

        String url = QueryUtils.buildURI(DefaultParameter.DEFAULT_BASE_URL, "us-news");
        Log.i(LOG_TAG, url);
    }
}
