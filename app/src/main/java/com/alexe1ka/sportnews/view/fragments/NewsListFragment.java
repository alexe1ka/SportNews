package com.alexe1ka.sportnews.view.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.alexe1ka.sportnews.R;
import com.alexe1ka.sportnews.view.adapters.NewsRvAdapter;
import com.alexe1ka.sportnews.viewmodel.NewsListViewModel;

public class NewsListFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    private NewsListViewModel mNewsListViewModel;

    private RecyclerView mNewsRv;
    private NewsRvAdapter mNewsRvAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private NavigationView mNavigationView;


    public static final String TAG = NewsListFragment.class.getSimpleName();

    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNavigationView = getActivity().findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);


        mNewsListViewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);
        mNewsListViewModel.init("football");
//        mNewsListViewModel.getEventsLiveData().observe(this, events -> {
//            Log.d(TAG, "onActivityCreated: OBSERVE_VIEW_MODEL"+events.getEvents().toString());
//            mNewsRvAdapter.setEvents(events);
//        });


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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_football:
                mNewsListViewModel.loadOtherEvents("football").observe(this, events -> {
                    mNewsRvAdapter.setEvents(events);
                });
                break;
            case R.id.nav_hockey:
                mNewsListViewModel.loadOtherEvents("hockey").observe(this, events -> mNewsRvAdapter.setEvents(events));
                break;
            case R.id.nav_tennis:
                mNewsListViewModel.loadOtherEvents("tennis").observe(this, events -> mNewsRvAdapter.setEvents(events));

                break;
            case R.id.nav_basketball:
                mNewsListViewModel.loadOtherEvents("basketball").observe(this, events -> mNewsRvAdapter.setEvents(events));
                break;
            case R.id.nav_volleyball:
                mNewsListViewModel.loadOtherEvents("volleyball").observe(this, events -> mNewsRvAdapter.setEvents(events));
                break;
            case R.id.nav_cybersport:
                mNewsListViewModel.loadOtherEvents("cybersport").observe(this, events -> mNewsRvAdapter.setEvents(events));
                break;
            default:
                throw new UnsupportedOperationException();
        }

        DrawerLayout drawer = this.getActivity().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
