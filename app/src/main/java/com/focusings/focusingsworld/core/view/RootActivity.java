package com.focusings.focusingsworld.core.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;

/**
 * Base {@link AppCompatActivity} class for every activity in this application.
 */
public abstract class RootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeInjector();
    }

    private void initializeInjector() {
        AndroidInjection.inject(this);
    }
}
