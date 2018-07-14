package com.focusings.focusingsworld.features.home.di;


import com.focusings.focusingsworld.features.home.mainchannel.view.MainChannelFragment;
import com.focusings.focusingsworld.features.home.mainchannel.di.MainChannelFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeActivityModule {

    @ContributesAndroidInjector(modules = MainChannelFragmentModule.class)
    abstract MainChannelFragment mainChannelFragment();
}
