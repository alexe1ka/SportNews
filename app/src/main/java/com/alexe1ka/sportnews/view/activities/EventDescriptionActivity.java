package com.alexe1ka.sportnews.view.activities;

import android.support.v4.app.Fragment;

import com.alexe1ka.sportnews.view.fragments.EventDescriptionFragment;

public class EventDescriptionActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return EventDescriptionFragment.newInstance();
    }
}
