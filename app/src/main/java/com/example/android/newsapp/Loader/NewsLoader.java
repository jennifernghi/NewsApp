package com.example.android.newsapp.Loader;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.android.newsapp.Utils.QueryUtils;
import com.example.android.newsapp.models.News;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jennifernghinguyen on 1/19/17.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    final static String LOG_TAG = NewsLoader.class.getSimpleName();
    private String baseurl;
    private String section;
    public NewsLoader(Context context, String baseurl, String section) {
        super(context);
        this.baseurl=baseurl;
        this.section=section;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        ArrayList<News> news;
        if((baseurl.length()<1 || baseurl ==null) || (section.length()<1 || section ==null)){
            return null;
        }

        String url = QueryUtils.buildURI(baseurl,section);

        Log.i(LOG_TAG, url);
        news = QueryUtils.fetchNewsData(url);

        return news;
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public void onCanceled(List<News> data) {
        super.onCanceled(data);

        data.clear();
    }
}
