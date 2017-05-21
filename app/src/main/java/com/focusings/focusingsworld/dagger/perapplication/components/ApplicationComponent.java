package com.focusings.focusingsworld.dagger.perapplication.components;

import com.focusings.focusingsworld.AndroidApplication;
import com.focusings.focusingsworld.dagger.perapplication.modules.ApplicationModule;
import com.focusings.focusingsworld.executor.PostExecutionThread;
import com.focusings.focusingsworld.executor.ThreadExecutor;
import com.focusings.focusingsworld.repository.YoutubeRepository;
import com.focusings.focusingsworld.repository.youtube.local.YoutubePrefsDataStore;
import com.focusings.focusingsworld.repository.youtube.remote.YoutubeRemoteDataStore;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();

    YoutubeRepository youtubeRepository();
    YoutubeRemoteDataStore youtubeRemoteDataStore();
    YoutubePrefsDataStore youtubePrefsDataStore();

    void inject(AndroidApplication androidApplication);
}