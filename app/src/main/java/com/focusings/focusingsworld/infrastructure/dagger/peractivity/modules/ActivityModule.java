package com.focusings.focusingsworld.infrastructure.dagger.peractivity.modules;

import android.app.Activity;
import android.content.Context;

import com.focusings.focusingsworld.infrastructure.dagger.PerActivity;
import com.focusings.focusingsworld.presentation.mainchannel.MainChannelModule;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module created to provide some common activity scope dependencies.
 * This module is going to be added to the graph generated for every activity while the activity
 * creation lifecycle.
 */
@Module(includes = {MainChannelModule.class})
public class ActivityModule {
}
