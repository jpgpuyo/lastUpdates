package com.focusings.focusingsworld.infrastructure.dagger.perapplication.modules;

import android.content.Context;

import com.focusings.focusingsworld.data.youtube.repository.recentvideos.RecentVideosRepository;
import com.focusings.focusingsworld.data.youtube.cache.PrefsCacheFactory;
import com.focusings.focusingsworld.data.youtube.repository.recentvideos.datasources.RecentVideosCloud;
import com.focusings.focusingsworld.data.youtube.api.YoutubeApi;
import com.focusings.focusingsworld.domain.repository.YoutubeRepository;
import com.github.pwittchen.prefser.library.Prefser;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {NetworkModule.class})
public class YoutubeModule {

    private Context context;

    public YoutubeModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @Singleton
    YoutubeRepository provideYoutubeRepository(RecentVideosCloud recentVideosCloud,
                                               PrefsCacheFactory prefsCacheFactory) {
        return new RecentVideosRepository(recentVideosCloud, prefsCacheFactory);
    }

    @Provides
    @Singleton
    RecentVideosCloud provideYoutubeRemoteDataStore(Retrofit retrofit) {
        YoutubeApi youtubeApi = retrofit.create(YoutubeApi.class);
        return new RecentVideosCloud(youtubeApi);
    }

    @Provides
    @Singleton
    PrefsCacheFactory providePrefsCacheFactory() {
        Prefser prefser = new Prefser(context.getSharedPreferences("youtubePrefs", Context.MODE_PRIVATE));
        return new PrefsCacheFactory(prefser);
    }
}
