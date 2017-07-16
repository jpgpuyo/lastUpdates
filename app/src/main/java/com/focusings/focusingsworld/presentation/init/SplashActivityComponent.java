package com.focusings.focusingsworld.presentation.init;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = {SplashActivityModule.class})
public interface SplashActivityComponent extends AndroidInjector<SplashActivity>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SplashActivity>{}
}
