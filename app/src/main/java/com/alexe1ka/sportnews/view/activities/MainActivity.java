package com.alexe1ka.sportnews.view.activities;

import android.support.v4.app.Fragment;

import com.alexe1ka.sportnews.view.fragments.NewsListFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return NewsListFragment.newInstance();
    }
}
