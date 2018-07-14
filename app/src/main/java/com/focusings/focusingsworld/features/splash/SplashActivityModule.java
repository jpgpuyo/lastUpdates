package com.focusings.focusingsworld.features.splash;

import com.focusings.focusingsworld.core.connectivity.Network;
import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.core.interactor.UseCase;
import com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data.RecentVideosRepository;
import com.focusings.focusingsworld.features.splash.initapp.InitApp;

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
