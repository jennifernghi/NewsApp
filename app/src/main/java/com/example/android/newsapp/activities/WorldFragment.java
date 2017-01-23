package com.example.android.newsapp.activities;

import android.content.Context;
import android.util.Log;

import com.example.android.newsapp.Utils.DefaultParameter;
import com.example.android.newsapp.Utils.GeneralParameter;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */
public class WorldFragment extends AbstractFragment {
    final String LOG_TAG = WorldFragment.class.getSimpleName();

    public WorldFragment() {
        super(DefaultParameter.DEFAULT_WORLD_CONSTANT, DefaultParameter.DEFAULT_WORLD_SECTION, DefaultParameter.DEFAULT_PAGE);
        Log.i(LOG_TAG, "in WorldFragment constructor");

    }


    @Override
    public void onStart() {
        super.onStart();

       // startLoading(DefaultParameter.DEFAULT_WORLD_CONSTANT);
        //setMaxPage(GeneralParameter.totalSizeWorldSection);
        //Log.i(LOG_TAG, "world max: " + getMaxPage());

    }


    @Override
    public void onResume() {
        super.onResume();
        reStartLoading(DefaultParameter.DEFAULT_WORLD_CONSTANT);
      //  setMaxPage(GeneralParameter.totalSizeWorldSection);
        //Log.i(LOG_TAG, "world max: " + getMaxPage());
    }
}
