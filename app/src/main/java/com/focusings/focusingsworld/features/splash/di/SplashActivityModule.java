package com.focusings.focusingsworld.features.splash.di;

import com.focusings.focusingsworld.core.connectivity.Network;
import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.core.interactor.UseCase;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosRepository;
import com.focusings.focusingsworld.features.splash.presenter.SplashAppPresenter;
import com.focusings.focusingsworld.features.splash.usecase.InitAppUseCase;

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
        return new InitAppUseCase(threadExecutor, postExecutionThread, network, recentVideosRepository);
    }
}
