package com.example.android.newsapp.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.android.newsapp.Loader.NewsLoader;
import com.example.android.newsapp.R;
import com.example.android.newsapp.Utils.DefaultParameter;
import com.example.android.newsapp.adapters.NewsAdapter;
import com.example.android.newsapp.models.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */

public abstract class AbstractFragment extends Fragment implements LoaderCallbacks<List<News>> {
    final String LOG_TAG = AbstractFragment.class.getSimpleName();
    private String section;
    private NewsAdapter adapter;
    private String baseUrl = DefaultParameter.DEFAULT_BASE_URL;
    private final int LOADER_CONSTANT = 1;
    private ViewHolder viewHolder;
    public AbstractFragment(String section) {
        this.section = section;
        Log.i(LOG_TAG, "in AbstractFragment constructor");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onCreateView");
         viewHolder = new ViewHolder();

        //inflate rootView using list_view
        viewHolder.rootView = inflater.inflate(R.layout.news_list_view, container, false);

        viewHolder.listView = (ListView) viewHolder.rootView.findViewById(R.id.list);
        adapter = new NewsAdapter(getActivity(), new ArrayList<News>());
        viewHolder.listView.setAdapter(adapter);

        viewHolder.loadingBar = (ProgressBar) viewHolder.rootView.findViewById(R.id.loading_bar);


        return viewHolder.rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "in onstart");

        startLoading(LOADER_CONSTANT);
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "in oncreateloader");
        return new NewsLoader(getActivity(), baseUrl, section);
    }

    static class ViewHolder {

        private ListView listView;
        private View rootView;
        private ProgressBar loadingBar;
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        Log.i(LOG_TAG, "in on loader reset");
        clearAdapter();
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        Log.i(LOG_TAG, "in on load finished");

        showProgressBar(true);
        clearAdapter();
        if (data != null && !data.isEmpty()) {
            showProgressBar(false);
            adapter.addAll(data);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "in on resume");
        clearAdapter();
        startLoading(LOADER_CONSTANT);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "in on pause");
        clearAdapter();
    }


    public void clearAdapter() {
        if (adapter != null) {
            adapter.clear();
        }
    }

    public void startLoading(int fragmentConstant) {
        Log.i(LOG_TAG, "initialize: start loading");
        getLoaderManager().initLoader(fragmentConstant, null, this);
    }


    public void showProgressBar(boolean on){
        if(on) {
            viewHolder.loadingBar.setVisibility(View.VISIBLE);

        }else{
            viewHolder.loadingBar.setVisibility(View.GONE);
        }
    }
}
