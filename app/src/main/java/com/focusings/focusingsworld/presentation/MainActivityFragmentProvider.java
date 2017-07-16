package com.focusings.focusingsworld.presentation;

import com.focusings.focusingsworld.presentation.mainchannel.MainChannelFragmentModule;
import com.focusings.focusingsworld.presentation.mainchannel.view.MainChannelFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityFragmentProvider {

    @ContributesAndroidInjector(modules = MainChannelFragmentModule.class)
    abstract MainChannelFragment mainChannelFragment();
}
