package com.focusings.focusingsworld.presentation.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.presentation.main.MainActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @Inject
    SplashAppPresenter splashAppPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);

        initializeInjector();

        splashAppPresenter.setView(this);
        splashAppPresenter.execute();
    }

    private void initializeInjector() {
        AndroidInjection.inject(this);
    }

    @Override
    public void onInitAppFinished() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
