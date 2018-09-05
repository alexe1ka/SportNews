package com.alexe1ka.sportnews.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexe1ka.sportnews.R;


public class EventDescriptionFragment extends Fragment {
    private TextView mTeam1Tv;
    private TextView mTeam2Tv;
    private TextView mTimeTv;
    private TextView mTournament1Tv;
    private TextView mPrediction1Tv;
    private TextView mHeadOfEventTv;
    private TextView mTextOfEventTv;

    public static EventDescriptionFragment newInstance() {
        return new EventDescriptionFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article,container,false);
        return view;
    }
}
