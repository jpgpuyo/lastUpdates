package com.focusings.focusingsworld.presentation;

import com.focusings.focusingsworld.presentation.mainchannel.MainChannelFragmentProvider;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = {
        MainActivityModule.class,
        MainChannelFragmentProvider.class})
public interface MainActivityComponent extends AndroidInjector<MainActivity>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{}
}
