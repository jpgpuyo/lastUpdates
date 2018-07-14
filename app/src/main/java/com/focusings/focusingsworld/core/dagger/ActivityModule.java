package com.focusings.focusingsworld.core.dagger;

import com.focusings.focusingsworld.features.home.MainActivity;
import com.focusings.focusingsworld.features.home.MainActivityModule;
import com.focusings.focusingsworld.features.splash.SplashActivity;
import com.focusings.focusingsworld.features.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();
}
