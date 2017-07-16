package com.focusings.focusingsworld.presentation.main;


import com.focusings.focusingsworld.presentation.main.mainchannel.MainChannelFragment;
import com.focusings.focusingsworld.presentation.main.mainchannel.MainChannelFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = MainChannelFragmentModule.class)
    abstract MainChannelFragment mainChannelFragment();
}
