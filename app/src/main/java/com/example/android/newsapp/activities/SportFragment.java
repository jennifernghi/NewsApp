package com.example.android.newsapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.android.newsapp.Utils.DefaultParameter;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */
public class SportFragment extends AbstractFragment {
    final int SPORT_FRAGMENT_CONSTANT =3;
    private boolean initialized = false;
    final String LOG_TAG = SportFragment.class.getSimpleName();
    public SportFragment() {
        super(DefaultParameter.DEFAULT_SPORT_SECTION);
        Log.i(LOG_TAG, "in sport fragment constructor");
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(this.isVisible() && !initialized){
            startLoading(1);
            initialized =true;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        reStartLoading(1);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
