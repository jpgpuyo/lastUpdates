package com.focusings.focusingsworld;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.focusings.focusingsworld.dagger.perapplication.components.ApplicationComponent;
import com.focusings.focusingsworld.dagger.perapplication.components.DaggerApplicationComponent;
import com.focusings.focusingsworld.dagger.perapplication.modules.ApplicationModule;
import com.focusings.focusingsworld.dagger.perapplication.modules.InitAppModule;
import com.focusings.focusingsworld.dagger.perapplication.modules.YoutubeModule;
import com.focusings.focusingsworld.init.InitAppPresenter;

import javax.inject.Inject;

public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Inject
    InitAppPresenter initAppPresenter;

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

        initAppPresenter.execute();
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .initAppModule(new InitAppModule())
                .youtubeModule(new YoutubeModule(this))
                .build();
        applicationComponent.inject(this);
    }
}
