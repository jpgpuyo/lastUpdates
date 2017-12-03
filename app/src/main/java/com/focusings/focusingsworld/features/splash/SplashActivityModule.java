package com.focusings.focusingsworld.features.splash;

import com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data.RecentVideosRepository;
import com.focusings.focusingsworld.features.splash.initapp.InitApp;
import com.jpuyo.android.infrastructure.connectivity.Network;
import com.jpuyo.android.infrastructure.executor.PostExecutionThread;
import com.jpuyo.android.infrastructure.executor.ThreadExecutor;
import com.jpuyo.android.infrastructure.interactor.UseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityModule {

    @Provides
    SplashAppPresenter provideInitAppPresenter(UseCase initAppUseCase){
        return new SplashAppPresenter(initAppUseCase);
    }

    @Provides
    UseCase provideInitAppUseCase(ThreadExecutor threadExecutor,
                                  PostExecutionThread postExecutionThread,
                                  Network network,
                                  RecentVideosRepository recentVideosRepository){
        return new InitApp(threadExecutor, postExecutionThread, network, recentVideosRepository);
    }
}
