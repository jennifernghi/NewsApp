package com.example.android.newsapp.activities;

import android.content.Context;
import android.util.Log;

import com.example.android.newsapp.Utils.DefaultParameter;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */
public class WorldFragment extends AbstractFragment {
    final String LOG_TAG = WorldFragment.class.getSimpleName();

    public WorldFragment() {
        super( DefaultParameter.DEFAULT_WORLD_SECTION);
        Log.i(LOG_TAG, "in WorldFragment constructor");

    }


    @Override
    public void onStart() {
        Log.i(LOG_TAG, "in on start");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(LOG_TAG, "in on resume");
        super.onResume();
    }
}
