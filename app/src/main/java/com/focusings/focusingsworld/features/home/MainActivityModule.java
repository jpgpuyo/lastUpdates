package com.focusings.focusingsworld.features.home;


import com.focusings.focusingsworld.features.home.mainchannel.MainChannelFragment;
import com.focusings.focusingsworld.features.home.mainchannel.MainChannelFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = MainChannelFragmentModule.class)
    abstract MainChannelFragment mainChannelFragment();
}
