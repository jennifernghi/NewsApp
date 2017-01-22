package com.example.android.newsapp.activities;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.android.newsapp.Utils.DefaultParameter;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */
public class TechFragment extends AbstractFragment {


    final String LOG_TAG = TechFragment.class.getSimpleName();

    public TechFragment() {
        super( DefaultParameter.DEFAULT_TECH_SECTION);
        Log.i(LOG_TAG, "in tech fragment constructor");
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
