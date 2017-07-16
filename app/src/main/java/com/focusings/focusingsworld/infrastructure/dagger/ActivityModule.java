package com.focusings.focusingsworld.infrastructure.dagger;

import com.focusings.focusingsworld.presentation.MainActivity;
import com.focusings.focusingsworld.presentation.MainActivityModule;
import com.focusings.focusingsworld.presentation.init.SplashActivity;
import com.focusings.focusingsworld.presentation.init.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();
}
