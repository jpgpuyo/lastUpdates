package com.focusings.focusingsworld.presentation.mainchannel;

import com.focusings.focusingsworld.infrastructure.dagger.PerActivity;
import com.focusings.focusingsworld.infrastructure.executor.PostExecutionThread;
import com.focusings.focusingsworld.infrastructure.executor.ThreadExecutor;
import com.focusings.focusingsworld.domain.interactors.GetRecentVideosFromChannel;
import com.focusings.focusingsworld.infrastructure.interactor.UseCase;
import com.focusings.focusingsworld.presentation.mainchannel.presenter.MainChannelPresenter;
import com.focusings.focusingsworld.domain.repository.YoutubeRepository;

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
    UseCase provideGetRecentVideosFromChannel(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            YoutubeRepository youtubeRepository){
        return new GetRecentVideosFromChannel(threadExecutor, postExecutionThread, youtubeRepository);
    }
}
