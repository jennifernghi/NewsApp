package com.example.android.newsapp.activities;

import android.util.Log;
import android.widget.Toast;

import com.example.android.newsapp.Utils.DefaultParameter;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */
public class WorldFragment extends AbstractFragment {
    final int WORLD_FRAGMENT_CONSTANT =1;
    private boolean initialized = false;
    final String LOG_TAG = WorldFragment.class.getSimpleName();

    public WorldFragment() {
        super(DefaultParameter.DEFAULT_WORLD_SECTION);
        Log.i(LOG_TAG, "in WorldFragment constructor");

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(this.isVisible() && !initialized){
           startLoading(1);
            initialized=true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        reStartLoading(1);
    }
}
