package com.alexe1ka.sportnews.view.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexe1ka.sportnews.R;
import com.alexe1ka.sportnews.view.adapters.NewsRvAdapter;
import com.alexe1ka.sportnews.viewmodel.NewsListViewModel;

public class NewsListFragment extends Fragment {
    private NewsListViewModel mNewsListViewModel;

    private RecyclerView mNewsRv;
    private NewsRvAdapter mNewsRvAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public static final String TAG = NewsListFragment.class.getSimpleName();

    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mNewsListViewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);
        mNewsListViewModel.init("");
        mNewsListViewModel.getEventsLiveData().observe(this, events -> mNewsRvAdapter.setEvents(events));

        mNewsRv = getActivity().findViewById(R.id.news_list_rv);
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mNewsRvAdapter = new NewsRvAdapter(this.getContext());
        mNewsRv.setLayoutManager(mLayoutManager);
        mNewsRv.setAdapter(mNewsRvAdapter);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        return view;
    }
}
