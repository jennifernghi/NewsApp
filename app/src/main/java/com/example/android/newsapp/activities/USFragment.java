package com.example.android.newsapp.activities;

import android.util.Log;
import android.widget.Toast;

import com.example.android.newsapp.Utils.DefaultParameter;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */
public class USFragment extends AbstractFragment {

    private boolean initialized = false;
    final String LOG_TAG = USFragment.class.getSimpleName();

    public USFragment() {
        super(DefaultParameter.DEFAULT_US_SECTION);
        Log.i(LOG_TAG, "USFragment constructor");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(this.isVisible() && !initialized && !this.isHidden()){
            //startLoading(1);
            Log.i(LOG_TAG, "start loading");
            initialized = true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(this.isVisible()){
            reStartLoading(1);
        }
    }
}
