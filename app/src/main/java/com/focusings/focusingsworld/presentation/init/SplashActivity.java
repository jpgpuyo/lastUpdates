package com.focusings.focusingsworld.presentation.init;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.focusings.focusingsworld.AndroidApplication;
import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.presentation.MainActivity;
import com.focusings.focusingsworld.presentation.init.presenter.InitAppPresenter;
import com.focusings.focusingsworld.presentation.init.view.SplashView;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @Inject
    InitAppPresenter initAppPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);

        initializeInjector();

        initAppPresenter.setView(this);
        initAppPresenter.execute();
    }

    private void initializeInjector() {
        ((AndroidApplication) getApplication()).getApplicationComponent().inject(this);
    }

    @Override
    public void onInitAppFinished() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
