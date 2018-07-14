package com.focusings.focusingsworld.core.di;

import com.focusings.focusingsworld.features.home.view.HomeActivity;
import com.focusings.focusingsworld.features.home.di.HomeActivityModule;
import com.focusings.focusingsworld.features.splash.view.SplashActivity;
import com.focusings.focusingsworld.features.splash.di.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity mainActivity();
}
