package com.focusings.focusingsworld.infrastructure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Base {@link Fragment} class for every fragment in this application.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeInjector();
    }

    protected abstract void initializeInjector();
}