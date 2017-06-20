package com.focusings.focusingsworld.infrastructure.dagger.perapplication.modules;

import android.content.Context;

import com.focusings.focusingsworld.data.RecentVideosFromChannelRepository;
import com.focusings.focusingsworld.data.youtube.YoutubeMockService;
import com.focusings.focusingsworld.data.youtube.cache.PrefsCacheFactory;
import com.focusings.focusingsworld.data.youtube.remote.YoutubeRemoteDataStore;
import com.focusings.focusingsworld.data.youtube.remote.YoutubeService;
import com.focusings.focusingsworld.domain.repository.YoutubeRepository;
import com.github.pwittchen.prefser.library.Prefser;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class YoutubeModule {

    private Context context;

    public YoutubeModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @Singleton
    YoutubeRepository provideYoutubeRepository(YoutubeRemoteDataStore youtubeRemoteDataStore,
                                               PrefsCacheFactory prefsCacheFactory) {
        return new RecentVideosFromChannelRepository(youtubeRemoteDataStore, prefsCacheFactory);
    }

    @Provides
    @Singleton
    YoutubeRemoteDataStore provideYoutubeRemoteDataStore() {
        YoutubeService youtubeService = new YoutubeMockService(context);
        return new YoutubeRemoteDataStore(youtubeService);
    }

    @Provides
    @Singleton
    PrefsCacheFactory providePrefsCacheFactory() {
        Prefser prefser = new Prefser(context.getSharedPreferences("youtubePrefs", Context.MODE_PRIVATE));
        return new PrefsCacheFactory(prefser);
    }
}
