package com.focusings.focusingsworld.presentation.mainchannel;

import com.focusings.focusingsworld.domain.interactors.GetRecentVideosFromChannel;
import com.focusings.focusingsworld.domain.repository.YoutubeRepository;
import com.focusings.focusingsworld.infrastructure.connectivity.Network;
import com.focusings.focusingsworld.infrastructure.dagger.PerActivity;
import com.focusings.focusingsworld.infrastructure.executor.PostExecutionThread;
import com.focusings.focusingsworld.infrastructure.executor.ThreadExecutor;
import com.focusings.focusingsworld.presentation.mainchannel.presenter.MainChannelPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainChannelModule {

    @Provides
    @PerActivity
    MainChannelPresenter provideMainChannelPresenter(GetRecentVideosFromChannel getYoutubeVideosFromChannelUseCase) {
        return new MainChannelPresenter(getYoutubeVideosFromChannelUseCase);
    }

    @Provides
    @PerActivity
    GetRecentVideosFromChannel provideGetRecentVideosFromChannel(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            Network network,
            YoutubeRepository youtubeRepository) {
        return new GetRecentVideosFromChannel(threadExecutor, postExecutionThread, network, youtubeRepository);
    }
}
