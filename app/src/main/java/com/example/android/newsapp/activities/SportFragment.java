package com.example.android.newsapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.newsapp.Utils.DefaultParameter;
import com.example.android.newsapp.Utils.GeneralParameter;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */
public class SportFragment extends AbstractFragment {
    final String LOG_TAG = SportFragment.class.getSimpleName();

    public SportFragment() {
        super(DefaultParameter.DEFAULT_SPORT_CONSTANT, DefaultParameter.DEFAULT_SPORT_SECTION, DefaultParameter.DEFAULT_PAGE);
        Log.i(LOG_TAG, "in sport fragment constructor");
    }


}
