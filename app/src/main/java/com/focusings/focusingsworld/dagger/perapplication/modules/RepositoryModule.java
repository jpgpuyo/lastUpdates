package com.focusings.focusingsworld.dagger.perapplication.modules;

import android.content.Context;

import com.focusings.focusingsworld.repository.YoutubeRepository;
import com.focusings.focusingsworld.repository.YoutubeRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    private Context context;

    public RepositoryModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    YoutubeRepository provideYoutubeRepository(){
        return new YoutubeRepositoryImpl();
    }
}
