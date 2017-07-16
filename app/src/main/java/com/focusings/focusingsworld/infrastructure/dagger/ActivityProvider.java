package com.focusings.focusingsworld.infrastructure.dagger;

import com.focusings.focusingsworld.presentation.MainActivity;
import com.focusings.focusingsworld.presentation.MainActivityModule;
import com.focusings.focusingsworld.presentation.init.SplashActivity;
import com.focusings.focusingsworld.presentation.init.SplashActivityModule;
import com.focusings.focusingsworld.presentation.MainActivityFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityProvider {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = {MainActivityModule.class, MainActivityFragmentProvider.class})
    abstract MainActivity bindMainActivity();
}
