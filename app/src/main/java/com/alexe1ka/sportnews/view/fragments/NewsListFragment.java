package com.alexe1ka.sportnews.view.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.alexe1ka.sportnews.R;
import com.alexe1ka.sportnews.SportNewsApp;
import com.alexe1ka.sportnews.network.InternetConnectionListener;
import com.alexe1ka.sportnews.network.InternetErrorListener;
import com.alexe1ka.sportnews.view.adapters.NewsRvAdapter;
import com.alexe1ka.sportnews.viewmodel.NewsListViewModel;

public class NewsListFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener, InternetConnectionListener, InternetErrorListener {
    private static final String TAG = NewsListFragment.class.getSimpleName();

    private NewsListViewModel mNewsListViewModel;

    private RecyclerView mNewsRv;
    private NewsRvAdapter mNewsRvAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private NavigationView mNavigationView;

    private ProgressBar mProgressBar;


    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNavigationView = getActivity().findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        ((SportNewsApp) getActivity().getApplication()).setInternetConnectionListener(this);
        ((SportNewsApp) getActivity().getApplication()).setInternetErrorListener(this);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        mProgressBar = getActivity().findViewById(R.id.loading_pb);
        mProgressBar.setVisibility(View.VISIBLE);

        mNewsListViewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);
        mNewsListViewModel.init("football");
        mNewsListViewModel.getEventsLiveData().observe(this, events -> {
            mNewsRvAdapter.setEvents(events);
            mProgressBar.setVisibility(View.GONE);
        });


        mNewsRv = getActivity().findViewById(R.id.news_list_rv);
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mNewsRvAdapter = new NewsRvAdapter(this.getContext());
        mNewsRv.setLayoutManager(mLayoutManager);
        mNewsRv.setAdapter(mNewsRvAdapter);

        DrawerLayout drawer = getActivity().findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mNewsRv.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        switch (item.getItemId()) {
            case R.id.nav_football:
                //TODO захардкоженные стринги
                getNewsForThisSport(getString(R.string.football));
                break;
            case R.id.nav_hockey:
                getNewsForThisSport(getString(R.string.hockey));
                break;
            case R.id.nav_tennis:
                getNewsForThisSport(getString(R.string.tennis));
                break;
            case R.id.nav_basketball:
                getNewsForThisSport(getString(R.string.basketball));
                break;
            case R.id.nav_volleyball:
                getNewsForThisSport(getString(R.string.volleyball));
                break;
            case R.id.nav_cybersport:
                getNewsForThisSport(getString(R.string.cybersport));
                break;
            default:
                throw new UnsupportedOperationException();
        }

        DrawerLayout drawer = this.getActivity().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getNewsForThisSport(String sport) {
        mNewsListViewModel.loadOtherEvents(sport).observe(this, events -> {
            mNewsRvAdapter.setEvents(events);
            mProgressBar.setVisibility(View.GONE);
            mNewsRv.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void onInternetUnavailable() {
        //можно добавить в снакбар переход в настройки для включения интернета
        errorSnackbar("Internet unavailable");
    }

    public void errorSnackbar(String text) {
        mProgressBar.setVisibility(View.GONE);
        Snackbar snackbar = Snackbar.make(getView(), text, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onError(String error) {
        errorSnackbar(error);
    }
}
