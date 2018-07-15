package com.focusings.focusingsworld.core.di;

import android.app.Application;
import android.content.Context;

import com.focusings.focusingsworld.core.executor.JobExecutor;
import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.core.executor.UIThread;
import com.focusings.focusingsworld.data.youtube.core.memory.MemoryYoutubeDataStore;
import com.focusings.focusingsworld.data.youtube.recentvideos.CloudRecentVideosDataStore;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor() {
        return new JobExecutor();
    }


    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread() {
        return new UIThread();
    }

    @Provides
    @Singleton
    RecentVideosRepository provideRecentVideosRepository(CloudRecentVideosDataStore cloudRecentVideosDataStore,
                                                         MemoryYoutubeDataStore memoryYoutubeDataStore) {
        return new RecentVideosRepository(cloudRecentVideosDataStore, memoryYoutubeDataStore);
    }

    @Provides
    @Singleton
    MemoryYoutubeDataStore provideMemoryYoutubeDataStore() {
        return new MemoryYoutubeDataStore();
    }
}
