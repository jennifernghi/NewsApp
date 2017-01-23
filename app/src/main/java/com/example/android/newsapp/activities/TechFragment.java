package com.example.android.newsapp.activities;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.android.newsapp.Utils.DefaultParameter;
import com.example.android.newsapp.Utils.GeneralParameter;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */
public class TechFragment extends AbstractFragment {


    final String LOG_TAG = TechFragment.class.getSimpleName();

    public TechFragment() {
        super(DefaultParameter.DEFAULT_TECH_CONSTANT, DefaultParameter.DEFAULT_TECH_SECTION, DefaultParameter.DEFAULT_PAGE);
        Log.i(LOG_TAG, "in tech fragment constructor");
    }


    @Override
    public void onStart() {
        super.onStart();

        startLoading(DefaultParameter.DEFAULT_TECH_CONSTANT);
        setMaxPage(GeneralParameter.totalSizeTechSection);

    }


    @Override
    public void onResume() {
        super.onResume();
        reStartLoading(DefaultParameter.DEFAULT_TECH_CONSTANT);
        setMaxPage(GeneralParameter.totalSizeTechSection);
    }
}
