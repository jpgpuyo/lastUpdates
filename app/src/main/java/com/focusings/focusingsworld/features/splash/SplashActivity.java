package com.focusings.focusingsworld.features.splash;

import android.content.Intent;
import android.os.Bundle;

import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.infrastructure.RootActivity;
import com.focusings.focusingsworld.features.home.MainActivity;

import javax.inject.Inject;

public class SplashActivity extends RootActivity implements SplashView {

    @Inject
    SplashAppPresenter splashAppPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);

        splashAppPresenter.setView(this);
        splashAppPresenter.execute();
    }

    @Override
    public void onInitAppFinished() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
