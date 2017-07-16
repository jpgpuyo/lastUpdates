package com.focusings.focusingsworld.presentation.init;

import com.focusings.focusingsworld.domain.interactors.init.InitApp;
import com.focusings.focusingsworld.domain.repository.YoutubeRepository;
import com.focusings.focusingsworld.presentation.init.presenter.SplashAppPresenter;
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
                                  YoutubeRepository youtubeRepository){
        return new InitApp(threadExecutor, postExecutionThread, network, youtubeRepository);
    }
}
