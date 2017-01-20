package com.example.android.newsapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
    private LoaderManager loaderManager;
    private String baseUrl = DefaultParameter.DEFAULT_BASE_URL;

    public AbstractFragment(String section) {
        this.section = section;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onCreateView");
        ViewHolder viewHolder = new ViewHolder();

        //inflate rootView using list_view
        viewHolder.rootView = inflater.inflate(R.layout.news_list_view, container, false);

        viewHolder.listView = (ListView) viewHolder.rootView.findViewById(R.id.list);
        adapter = new NewsAdapter(getActivity(), new ArrayList<News>());
        viewHolder.listView.setAdapter(adapter);




        return viewHolder.rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        loaderManager = getLoaderManager();

        loaderManager.initLoader(1, null, this);
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(getActivity(), baseUrl, section);
    }

    static class ViewHolder {

        private ListView listView;
        private View rootView;
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        adapter.clear();
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        if(data!=null && !data.isEmpty()){
            adapter.addAll(data);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.clear();
        loaderManager.restartLoader(1, null, this);
    }

    @Override
    public void onPause() {
        super.onPause();
        adapter.clear();
    }
}
