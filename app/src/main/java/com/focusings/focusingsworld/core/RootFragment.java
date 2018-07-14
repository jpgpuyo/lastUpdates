package com.focusings.focusingsworld.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import dagger.android.support.AndroidSupportInjection;

/**
 * Base {@link Fragment} class for every fragment in this application.
 */
public abstract class RootFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeInjector();
    }

    private void initializeInjector() {
        AndroidSupportInjection.inject(this);
    }
}