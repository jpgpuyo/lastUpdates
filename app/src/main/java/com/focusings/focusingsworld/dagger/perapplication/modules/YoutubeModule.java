package com.focusings.focusingsworld.dagger.perapplication.modules;

import android.content.Context;

import com.focusings.focusingsworld.repository.YoutubeRepository;
import com.focusings.focusingsworld.repository.YoutubeRepositoryImpl;
import com.focusings.focusingsworld.repository.youtube.local.YoutubeLocalDataStore;
import com.focusings.focusingsworld.repository.youtube.local.YoutubePrefsDataStore;
import com.focusings.focusingsworld.repository.youtube.remote.YoutubeRemoteDataStore;
import com.focusings.focusingsworld.repository.youtube.remote.YoutubeService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {NetworkModule.class, LocalModule.class})
public class YoutubeModule {

    private Context context;

    public YoutubeModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @Singleton
    YoutubeRepository provideYoutubeRepository(YoutubeRemoteDataStore youtubeRemoteDataStore,
                                               YoutubeLocalDataStore youtubeLocalDataStore) {
        return new YoutubeRepositoryImpl(youtubeRemoteDataStore, youtubeLocalDataStore);
    }

    @Provides
    @Singleton
    YoutubeRemoteDataStore provideYoutubeRemoteDataStore(Retrofit retrofit) {
        YoutubeService youtubeService = retrofit.create(YoutubeService.class);
        return new YoutubeRemoteDataStore(youtubeService);
    }

    @Provides
    @Singleton
    YoutubePrefsDataStore provideYoutubePrefsDataStore() {
        return new YoutubePrefsDataStore(context);
    }
}
