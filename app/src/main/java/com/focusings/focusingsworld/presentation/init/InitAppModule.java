package com.focusings.focusingsworld.presentation.init;

import com.focusings.focusingsworld.presentation.init.presenter.InitAppPresenter;
import com.focusings.focusingsworld.domain.interactors.init.InitApp;

import com.focusings.focusingsworld.domain.repository.YoutubeRepository;
import com.jpuyo.android.infrastructure.connectivity.Network;
import com.jpuyo.android.infrastructure.executor.PostExecutionThread;
import com.jpuyo.android.infrastructure.executor.ThreadExecutor;
import com.jpuyo.android.infrastructure.interactor.UseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class InitAppModule {

    @Provides
    @Singleton
    InitAppPresenter provideInitAppPresenter(UseCase initAppUseCase){
        return new InitAppPresenter(initAppUseCase);
    }

    @Provides
    @Singleton
    UseCase provideInitAppUseCase(ThreadExecutor threadExecutor,
                                  PostExecutionThread postExecutionThread,
                                  Network network,
                                  YoutubeRepository youtubeRepository){
        return new InitApp(threadExecutor, postExecutionThread, network, youtubeRepository);
    }
}
