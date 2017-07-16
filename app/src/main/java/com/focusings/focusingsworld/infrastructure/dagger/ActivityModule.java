package com.focusings.focusingsworld.infrastructure.dagger;

import com.focusings.focusingsworld.presentation.main.MainActivity;
import com.focusings.focusingsworld.presentation.main.MainActivityModule;
import com.focusings.focusingsworld.presentation.splash.SplashActivity;
import com.focusings.focusingsworld.presentation.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();
}
