package com.focusings.focusingsworld.presentation.mainchannel;

import com.focusings.focusingsworld.domain.interactors.recentvideos.GetRecentVideosFromChannel;
import com.focusings.focusingsworld.domain.repository.YoutubeRepository;
import com.focusings.focusingsworld.presentation.mainchannel.presenter.MainChannelPresenter;
import com.jpuyo.android.infrastructure.connectivity.Network;
import com.jpuyo.android.infrastructure.executor.PostExecutionThread;
import com.jpuyo.android.infrastructure.executor.ThreadExecutor;

import dagger.Module;
import dagger.Provides;

@Module
public class MainChannelFragmentModule {

    @Provides
    MainChannelPresenter provideMainChannelPresenter(GetRecentVideosFromChannel getYoutubeVideosFromChannelUseCase) {
        return new MainChannelPresenter(getYoutubeVideosFromChannelUseCase);
    }

    @Provides
    GetRecentVideosFromChannel provideGetRecentVideosFromChannel(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            Network network,
            YoutubeRepository youtubeRepository) {
        return new GetRecentVideosFromChannel(threadExecutor, postExecutionThread, network, youtubeRepository);
    }

}
