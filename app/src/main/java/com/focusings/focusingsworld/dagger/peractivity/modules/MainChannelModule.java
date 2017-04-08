package com.focusings.focusingsworld.dagger.peractivity.modules;

import com.focusings.focusingsworld.dagger.PerActivity;
import com.focusings.focusingsworld.executor.PostExecutionThread;
import com.focusings.focusingsworld.executor.ThreadExecutor;
import com.focusings.focusingsworld.interactor.GetYoutubeVideosFromChannelUseCase;
import com.focusings.focusingsworld.interactor.UseCase;
import com.focusings.focusingsworld.mainchannel.presenter.MainChannelPresenter;
import com.focusings.focusingsworld.repository.YoutubeRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class MainChannelModule {

    @Provides
    @PerActivity
    MainChannelPresenter provideMainChannelPresenter(UseCase getYoutubeVideosFromChannelUseCase){
        return new MainChannelPresenter(getYoutubeVideosFromChannelUseCase);
    }

    @Provides
    @PerActivity
    UseCase provideGetYoutubeVideosFromChannelUseCase(
            ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, YoutubeRepository youtubeRepository){
        return new GetYoutubeVideosFromChannelUseCase(threadExecutor, postExecutionThread, youtubeRepository);
    }
}
