package com.focusings.focusingsworld.features.home.mainchannel;

import com.focusings.focusingsworld.core.connectivity.Network;
import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.features.home.mainchannel.recentvideos.GetRecentVideosFromChannel;
import com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data.RecentVideosRepository;

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
            RecentVideosRepository recentVideosRepository) {
        return new GetRecentVideosFromChannel(threadExecutor, postExecutionThread, network, recentVideosRepository);
    }

}
