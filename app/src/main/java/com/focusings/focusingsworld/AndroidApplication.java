package com.focusings.focusingsworld;

import android.app.Application;

import com.focusings.focusingsworld.dagger.perapplication.components.ApplicationComponent;
import com.focusings.focusingsworld.dagger.perapplication.components.DaggerApplicationComponent;
import com.focusings.focusingsworld.dagger.perapplication.modules.ApplicationModule;
import com.focusings.focusingsworld.dagger.perapplication.modules.YoutubeModule;

public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .youtubeModule(new YoutubeModule(this))
                .build();
    }
}
