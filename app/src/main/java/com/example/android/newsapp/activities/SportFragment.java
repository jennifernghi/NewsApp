package com.example.android.newsapp.activities;

import android.util.Log;

import com.example.android.newsapp.Utils.DefaultParameter;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */
public class SportFragment extends AbstractFragment {
    final String LOG_TAG = SportFragment.class.getSimpleName();
    public SportFragment() {
        super(DefaultParameter.DEFAULT_SPORT_SECTION);
        Log.i(LOG_TAG, "in sport fragment constructor");
    }


    /*@Override
    public void onStart() {
        super.onStart();
        if(this.isVisible()){
            getLoaderManager().initLoader(1, null, this);
        }
    }*/


   /* @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(this.isVisible()){
            getLoaderManager().initLoader(1, null, this);
        }
    }*/
}
