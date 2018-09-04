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
import android.widget.Toast;

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_football) {
            Toast.makeText(this.getContext(), "Football",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_hockey) {
            Toast.makeText(this.getContext(), "Hockey",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_tennis) {
            Toast.makeText(this.getContext(), "tennis",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_basketball) {
            Toast.makeText(this.getContext(), "basketball",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_volleyball) {
            Toast.makeText(this.getContext(), "volleyball",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_cybersport) {
            Toast.makeText(this.getContext(), "cybersport",Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer =this.getActivity().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
