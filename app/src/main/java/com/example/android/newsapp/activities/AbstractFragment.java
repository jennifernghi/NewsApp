package com.example.android.newsapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.newsapp.R;
import com.example.android.newsapp.adapters.NewsAdapter;
import com.example.android.newsapp.models.News;

import java.util.ArrayList;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */

public abstract class AbstractFragment extends Fragment {

    static class ViewHolder {
        private ListView listView;
        private View rootView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewHolder viewHolder = new ViewHolder();

        //inflate rootView using list_view
        viewHolder.rootView = inflater.inflate(R.layout.news_list_view, container, false);

        viewHolder.listView = (ListView) viewHolder.rootView.findViewById(R.id.list);
        viewHolder.listView.setAdapter(new NewsAdapter(getActivity(), new ArrayList<News>()));
        return viewHolder.rootView;
    }
}
