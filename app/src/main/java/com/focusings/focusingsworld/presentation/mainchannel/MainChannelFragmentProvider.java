package com.focusings.focusingsworld.presentation.mainchannel;

import android.support.v4.app.Fragment;

import com.focusings.focusingsworld.presentation.mainchannel.view.MainChannelFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainChannelFragmentProvider {

    @Binds
    @IntoMap
    @FragmentKey(MainChannelFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> provideDetailFragmentFactory(MainChannelFragmentComponent.Builder builder);
}
