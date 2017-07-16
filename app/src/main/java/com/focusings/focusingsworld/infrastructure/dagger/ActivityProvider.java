package com.focusings.focusingsworld.infrastructure.dagger;

import android.app.Activity;

import com.focusings.focusingsworld.presentation.MainActivity;
import com.focusings.focusingsworld.presentation.MainActivityComponent;
import com.focusings.focusingsworld.presentation.init.SplashActivity;
import com.focusings.focusingsworld.presentation.init.SplashActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module
public abstract class ActivityProvider {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivity(MainActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(SplashActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindSplashActivity(SplashActivityComponent.Builder builder);
}
