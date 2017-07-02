package com.focusings.focusingsworld.infrastructure.dagger.perapplication.components;

import com.focusings.focusingsworld.AndroidApplication;
import com.focusings.focusingsworld.infrastructure.connectivity.Network;
import com.focusings.focusingsworld.infrastructure.dagger.perapplication.modules.ApplicationModule;
import com.focusings.focusingsworld.infrastructure.executor.PostExecutionThread;
import com.focusings.focusingsworld.infrastructure.executor.ThreadExecutor;
import com.focusings.focusingsworld.domain.repository.YoutubeRepository;
import com.focusings.focusingsworld.data.youtube.cache.PrefsCacheFactory;
import com.focusings.focusingsworld.data.youtube.repository.recentvideos.datasources.RecentVideosCloud;
import com.focusings.focusingsworld.presentation.init.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    Network network();

    YoutubeRepository youtubeRepository();
    RecentVideosCloud youtubeRemoteDataStore();
    PrefsCacheFactory prefsCacheFactory();

    void inject(AndroidApplication androidApplication);
    void inject(SplashActivity splashActivity);
}