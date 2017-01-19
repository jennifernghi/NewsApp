package com.example.android.newsapp.Loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.android.newsapp.Utils.QueryUtils;
import com.example.android.newsapp.models.News;

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
        List<News> news;
        if((baseurl.length()<1 || baseurl ==null) || (section.length()<1 || section ==null)){
            return null;
        }

        news = QueryUtils.fetchNewsData(baseurl);
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
