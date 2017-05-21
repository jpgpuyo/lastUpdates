package com.focusings.focusingsworld.dagger.perapplication.modules;

import com.focusings.focusingsworld.repository.youtube.local.YoutubeLocalDataStore;
import com.focusings.focusingsworld.repository.youtube.local.YoutubePrefsDataStore;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LocalModule {

    @Binds
    abstract YoutubeLocalDataStore bindsYoutubeLocalDataStore(YoutubePrefsDataStore youtubePrefsDataStore);
}
