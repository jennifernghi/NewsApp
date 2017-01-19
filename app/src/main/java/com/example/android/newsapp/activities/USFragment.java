package com.example.android.newsapp.activities;

import android.util.Log;

import com.example.android.newsapp.Utils.DefaultParameter;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */
public class USFragment extends AbstractFragment {
    final String LOG_TAG = USFragment.class.getSimpleName();

    public USFragment() {
        super(DefaultParameter.DEFAULT_US_SECTION);
        Log.i(LOG_TAG, "USFragment constructor");
    }
}
