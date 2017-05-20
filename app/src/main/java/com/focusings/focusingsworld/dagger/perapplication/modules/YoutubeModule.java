package com.focusings.focusingsworld.dagger.perapplication.modules;

import android.content.Context;

import com.focusings.focusingsworld.repository.YoutubeRepository;
import com.focusings.focusingsworld.repository.YoutubeRepositoryImpl;
import com.focusings.focusingsworld.repository.youtube.memory.RecentVideosCache;
import com.focusings.focusingsworld.repository.youtube.remote.YoutubeRemoteDataStore;
import com.focusings.focusingsworld.repository.youtube.remote.YoutubeService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {NetworkModule.class})
public class YoutubeModule {

    private Context context;

    public YoutubeModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    YoutubeRepository provideYoutubeRepository(YoutubeRemoteDataStore youtubeRemoteDataStore,
                                               RecentVideosCache recentVideosCache) {
        return new YoutubeRepositoryImpl(youtubeRemoteDataStore, recentVideosCache);
    }

    @Provides
    @Singleton
    YoutubeRemoteDataStore provideYoutubeRemoteDataStore(Retrofit retrofit) {
        YoutubeService youtubeService = retrofit.create(YoutubeService.class);
        return new YoutubeRemoteDataStore(youtubeService);
    }

    @Provides
    @Singleton
    RecentVideosCache provideRecentVideosCache() {
        return new RecentVideosCache();
    }
}
