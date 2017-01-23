package com.example.android.newsapp.activities;

import android.content.Context;
import android.util.Log;

import com.example.android.newsapp.Utils.DefaultParameter;
import com.example.android.newsapp.Utils.GeneralParameter;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */
public class USFragment extends AbstractFragment {


    final String LOG_TAG = USFragment.class.getSimpleName();


    public USFragment() {
        super(DefaultParameter.DEFAULT_US_CONSTANT, DefaultParameter.DEFAULT_US_SECTION, DefaultParameter.DEFAULT_PAGE);
        Log.i(LOG_TAG, "USFragment constructor");
    }

}
