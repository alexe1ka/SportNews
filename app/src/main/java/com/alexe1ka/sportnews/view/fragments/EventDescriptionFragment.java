package com.alexe1ka.sportnews.view.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alexe1ka.sportnews.R;
import com.alexe1ka.sportnews.model.articles.Article;
import com.alexe1ka.sportnews.model.articles.ArticleDescription;
import com.alexe1ka.sportnews.viewmodel.NewsDescriptionViewModel;


public class EventDescriptionFragment extends Fragment {
    public static String URL_EXTRA = "url";

    private NewsDescriptionViewModel mNewsDescriptionViewModel;
    private ListView mListView;

//    private TextView mTeam1Tv;
//    private TextView mTeam2Tv;
//    private TextView mTimeTv;
//    private TextView mTournamentTv;
//    private TextView mPredictionTv;
//    private TextView mHeadOfEventTv;
//    private TextView mTextOfEventTv;

    public static EventDescriptionFragment newInstance() {
        return new EventDescriptionFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //в ответе с апи на самом деле не названия команда,а слова СТАТИСТИКА и ГИД
//        mTeam1Tv = getActivity().findViewById(R.id.art_team1_tv);
//        mTeam2Tv = getActivity().findViewById(R.id.art_team2_tv);
//
//        mTimeTv = getActivity().findViewById(R.id.art_time_tv);
//
//        mTournamentTv = getActivity().findViewById(R.id.art_tournament_tv);
//
//        mPredictionTv = getActivity().findViewById(R.id.art_prediction_tv);
//
//        mHeadOfEventTv = getActivity().findViewById(R.id.art_head_of_event_tv);
//
//        mTextOfEventTv = getActivity().findViewById(R.id.art_text_of_event_tv);

        mListView = getActivity().findViewById(R.id.articles_list_view);

        String url = getActivity().getIntent().getStringExtra(URL_EXTRA);
        mNewsDescriptionViewModel = ViewModelProviders.of(this).get(NewsDescriptionViewModel.class);
        mNewsDescriptionViewModel.init(url);
        mNewsDescriptionViewModel.getArticleDescriptionMutableLiveData().observe(this, new Observer<ArticleDescription>() {
            @Override
            public void onChanged(@Nullable ArticleDescription articleDescription) {
                ArrayAdapter<Article> itemsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, articleDescription.getArticle());
                mListView.setAdapter(itemsAdapter);
//                mTeam1Tv.setText(articleDescription.getTeam1());
//                mTeam2Tv.setText(articleDescription.getTeam2());
//                mTimeTv.setText(articleDescription.getTime());
//                mTournamentTv.setText(articleDescription.getTournament());
//                mPredictionTv.setText(articleDescription.getPrediction());
//                mHeadOfEventTv.setText(articleDescription.getArticle().toString());
//                mTextOfEventTv.setText("");
            }
        });
//        Toast.makeText(getContext(), url, Toast.LENGTH_LONG).show();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        return view;
    }
}
