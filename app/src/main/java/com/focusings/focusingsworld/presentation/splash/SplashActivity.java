package com.focusings.focusingsworld.presentation.splash;

import android.content.Intent;
import android.os.Bundle;

import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.infrastructure.BaseActivity;
import com.focusings.focusingsworld.presentation.main.MainActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashView {

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
