package com.example.android.newsapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.android.newsapp.listener.FragmentLifeCycleListener;
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
    private final int LOADER_CONSTANT =1;

    public AbstractFragment(String section) {
        this.section = section;
        Log.i(LOG_TAG, "in AbstractFragment constructor");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(LOG_TAG, "on Create");
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
        Log.i(LOG_TAG, "in onstart");

        startLoading(1);
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "in oncreateloader");
        return new NewsLoader(getActivity(), baseUrl, section);
    }

    static class ViewHolder {

        private ListView listView;
        private View rootView;
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        Log.i(LOG_TAG, "in on loader reset");
        adapter.clear();
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        Log.i(LOG_TAG, "in on load finished");
        adapter.clear();
        if(data!=null && !data.isEmpty()){
            adapter.addAll(data);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "in on resume");
        adapter.clear();
       // reStartLoading(LOADER_CONSTANT);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "in on pause");
        adapter.clear();
    }


    public void clearAdapter(){
        if(adapter!=null) {
            adapter.clear();
        }
    }
    public void startLoading(int fragmentConstant){
        getLoaderManager().initLoader(fragmentConstant, null, this);
    }

    public void reStartLoading(int fragmentConstant){
        getLoaderManager().restartLoader(fragmentConstant, null, this);
    }

   /* @Override
    public void onPauseTask() {
        Log.i(LOG_TAG, "in on pause task");
       // adapter.clear();
    }

    @Override
    public void onResumeTask() {
        Log.i(LOG_TAG, "in on resume task");
        //adapter.clear();
    }*/
}
