package com.focusings.focusingsworld.presentation.mainchannel;

import com.focusings.focusingsworld.presentation.mainchannel.view.MainChannelFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = MainChannelFragmentModule.class)
public interface MainChannelFragmentComponent extends AndroidInjector<MainChannelFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainChannelFragment> {
    }
}
