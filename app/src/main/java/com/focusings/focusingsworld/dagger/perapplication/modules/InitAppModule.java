package com.focusings.focusingsworld.dagger.perapplication.modules;

import com.focusings.focusingsworld.executor.PostExecutionThread;
import com.focusings.focusingsworld.executor.ThreadExecutor;
import com.focusings.focusingsworld.init.InitAppPresenter;
import com.focusings.focusingsworld.interactor.InitAppUseCase;
import com.focusings.focusingsworld.interactor.UseCase;
import com.focusings.focusingsworld.repository.YoutubeRepository;

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
                                  YoutubeRepository youtubeRepository){
        return new InitAppUseCase(threadExecutor, postExecutionThread, youtubeRepository);
    }
}
