package com.example.android.newsapp.activities;

import android.util.Log;
import android.widget.Toast;

import com.example.android.newsapp.Utils.DefaultParameter;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */
public class TechFragment extends AbstractFragment {


    final String LOG_TAG = TechFragment.class.getSimpleName();

    public TechFragment() {
        super(DefaultParameter.DEFAULT_TECH_SECTION, DefaultParameter.DEFAULT_TECH_CONSTANT);
        Log.i(LOG_TAG, "in tech fragment constructor");
    }




}
