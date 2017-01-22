package com.example.android.newsapp.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.newsapp.Loader.NewsLoader;
import com.example.android.newsapp.R;
import com.example.android.newsapp.Utils.DefaultParameter;
import com.example.android.newsapp.adapters.NewsAdapter;
import com.example.android.newsapp.models.News;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */

public abstract class AbstractFragment extends Fragment implements LoaderCallbacks<List<News>> {
    final String LOG_TAG = AbstractFragment.class.getSimpleName();
    private String section;
    private NewsAdapter adapter;
    private String baseUrl = DefaultParameter.DEFAULT_BASE_URL;
    private ViewHolder viewHolder;


    public AbstractFragment(String section) {
        this.section = section;
        Log.i(LOG_TAG, "in AbstractFragment constructor");
    }

    static class ViewHolder {

        private ListView listView;
        private View rootView;
        private ProgressBar loadingBar;
        private TextView loadingText;
        private LinearLayout emptyView;
        private ImageView emptyViewImage;
        private TextView emptyViewText;
        private Button emptyViewButton;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onCreateView");
        viewHolder = new ViewHolder();
        if (savedInstanceState != null) {
            setUserVisibleHint(true);
        }
        populateViews(viewHolder, inflater, container);


        adapter = new NewsAdapter(getActivity(), new ArrayList<News>());
        viewHolder.listView.setAdapter(adapter);

        viewHolder.listView.setEmptyView(viewHolder.emptyView);
        enableEmptyView(false);


        return viewHolder.rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        startLoading(DefaultParameter.LOADER_CONSTANT);

    }


    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {


        return new NewsLoader(getActivity(), baseUrl, section);
    }


    @Override
    public void onLoaderReset(Loader<List<News>> loader) {


        clearAdapter();
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {

        clearAdapter();
        enableEmptyView(true);


        if (data != null && !data.isEmpty()) {
            showProgressBar(false);
            adapter.addAll(data);
        } else {
            enableEmptyView(true);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        reStartLoading(DefaultParameter.LOADER_CONSTANT);
    }


    public void clearAdapter() {
        if (adapter != null) {
            adapter.clear();

        }
    }

    public void startLoading(int fragmentConstant) {

        if (checkNetWorkConnection()) {
            showProgressBar(true);
            enableEmptyView(false);
            clearAdapter();
            getLoaderManager().initLoader(fragmentConstant, null, this).forceLoad();
        } else {
            enableEmptyView(true);
            setEmptyView(R.drawable.disconnect, "Check network connection!", "Try Again!");

        }

    }

    public void reStartLoading(int fragmentConstant) {

        if (checkNetWorkConnection()) {
            showProgressBar(true);
            enableEmptyView(false);
            clearAdapter();
            getLoaderManager().getLoader(fragmentConstant).forceLoad();
        } else {
            enableEmptyView(true);
            setEmptyView(R.drawable.disconnect, "Check network connection!", "Try Again!");

        }

    }


    public void showProgressBar(boolean on) {
        if (on) {
            viewHolder.loadingText.setVisibility(View.VISIBLE);
            viewHolder.loadingBar.setVisibility(View.VISIBLE);
        } else {
            viewHolder.loadingText.setVisibility(View.GONE);
            viewHolder.loadingBar.setVisibility(View.GONE);
        }
    }

    public void enableEmptyView(boolean visibility) {
        if (visibility) {
            viewHolder.emptyView.setVisibility(View.VISIBLE);
            viewHolder.emptyViewImage.setVisibility(View.VISIBLE);
            viewHolder.emptyViewText.setVisibility(View.VISIBLE);
            viewHolder.emptyViewButton.setVisibility(View.VISIBLE);

        } else {
            viewHolder.emptyView.setVisibility(View.GONE);
            viewHolder.emptyViewImage.setVisibility(View.GONE);
            viewHolder.emptyViewText.setVisibility(View.GONE);
            viewHolder.emptyViewButton.setVisibility(View.GONE);

        }
    }

    public void setEmptyView(int resId, String textView, String buttonText) {
        viewHolder.loadingBar.setVisibility(View.GONE);
        viewHolder.emptyView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.lightGray));
        viewHolder.emptyViewImage.setImageResource(resId);
        viewHolder.emptyViewText.setText(textView);
        viewHolder.emptyViewButton.setText(buttonText);
        viewHolder.emptyViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startLoading(DefaultParameter.LOADER_CONSTANT);
            }
        });
    }


    public void populateViews(ViewHolder viewHolder, LayoutInflater inflater, ViewGroup container) {
        viewHolder.rootView = inflater.inflate(R.layout.news_list_view, container, false);
        viewHolder.emptyView = (LinearLayout) viewHolder.rootView.findViewById(R.id.empty_view_container);
        viewHolder.emptyView.setVisibility(View.GONE);
        viewHolder.listView = (ListView) viewHolder.rootView.findViewById(R.id.list);
        viewHolder.loadingBar = (ProgressBar) viewHolder.rootView.findViewById(R.id.loading_bar);
        viewHolder.loadingText = (TextView) viewHolder.rootView.findViewById(R.id.loading_text);
        viewHolder.emptyViewImage = (ImageView) viewHolder.rootView.findViewById(R.id.empty_view_image);
        viewHolder.emptyViewText = (TextView) viewHolder.rootView.findViewById(R.id.empty_view_text);
        viewHolder.emptyViewButton = (Button) viewHolder.rootView.findViewById(R.id.empty_view_button);

    }

    /**
     * check network connection
     *
     * @return boolean
     */
    private boolean checkNetWorkConnection() {

        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clearAdapter();
    }


}
