package com.example.android.newsapp.activities;

import android.util.Log;

import com.example.android.newsapp.Utils.DefaultParameter;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */
public class WorldFragment extends AbstractFragment {
    final String LOG_TAG = WorldFragment.class.getSimpleName();

    public WorldFragment() {
        super(DefaultParameter.DEFAULT_WORLD_CONSTANT, DefaultParameter.DEFAULT_WORLD_SECTION, DefaultParameter.DEFAULT_PAGE);
        Log.i(LOG_TAG, "in WorldFragment constructor");

    }


}
