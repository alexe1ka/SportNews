package com.alexe1ka.sportnews.view.fragments;

import android.arch.lifecycle.Observer;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alexe1ka.sportnews.R;
import com.alexe1ka.sportnews.model.articles.ArticleDescription;
import com.alexe1ka.sportnews.view.adapters.ArticleRvAdapter;
import com.alexe1ka.sportnews.viewmodel.NewsDescriptionViewModel;


public class EventDescriptionFragment extends Fragment {
    public static String URL_EXTRA = "url";

    private NewsDescriptionViewModel mNewsDescriptionViewModel;

    private TextView mTeam1Tv;
    private TextView mTeam2Tv;
    private TextView mTimeTv;
    private TextView mTournamentTv;
    private TextView mPredictionTv;

    private RecyclerView mArticleRv;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArticleRvAdapter mArticleRvAdapter;
    private ProgressBar mProgressBar;
    private View mDivider;


    public static EventDescriptionFragment newInstance() {
        return new EventDescriptionFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //в ответе с апи на самом деле не названия команда,а слова СТАТИСТИКА и ГИД
        mTeam1Tv = getActivity().findViewById(R.id.art_team1_tv);
        mTeam2Tv = getActivity().findViewById(R.id.art_team2_tv);
        mTimeTv = getActivity().findViewById(R.id.art_time_tv);
        mTournamentTv = getActivity().findViewById(R.id.art_tournament_tv);
        mPredictionTv = getActivity().findViewById(R.id.art_prediction_tv);
        mDivider = getActivity().findViewById(R.id.divider);

        mArticleRv = getActivity().findViewById(R.id.article_rv);
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mArticleRvAdapter = new ArticleRvAdapter(this.getContext());
        mArticleRv.setLayoutManager(mLayoutManager);
        mArticleRv.setAdapter(mArticleRvAdapter);
//        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar_article);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mProgressBar = getActivity().findViewById(R.id.article_pb);
        mProgressBar.setVisibility(View.VISIBLE);


        String url = getActivity().getIntent().getStringExtra(URL_EXTRA);
        mNewsDescriptionViewModel = ViewModelProviders.of(this).get(NewsDescriptionViewModel.class);
        mNewsDescriptionViewModel.init(url);
        mNewsDescriptionViewModel.getArticleDescriptionMutableLiveData().observe(this, new Observer<ArticleDescription>() {
            @Override
            public void onChanged(@Nullable ArticleDescription articleDescription) {
                mTeam1Tv.setText(articleDescription.getTeam1());
                mTeam2Tv.setText(articleDescription.getTeam2());
                mTimeTv.setText(articleDescription.getTime());
                mTournamentTv.setText(articleDescription.getTournament());
                mPredictionTv.setText(articleDescription.getPrediction());
                mArticleRvAdapter.setArticleList(articleDescription.getArticle());
                mProgressBar.setVisibility(View.GONE);
                mDivider.setVisibility(View.VISIBLE);


            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        return view;
    }
}
