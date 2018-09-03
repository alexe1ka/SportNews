package com.alexe1ka.sportnews;

import android.support.v4.app.Fragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return NewsListFragment.newInstance();
    }
}
