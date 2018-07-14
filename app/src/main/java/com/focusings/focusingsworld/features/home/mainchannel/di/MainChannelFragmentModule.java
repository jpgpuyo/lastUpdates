package com.focusings.focusingsworld.features.home.mainchannel.di;

import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.features.home.mainchannel.presenter.MainChannelPresenter;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosRepository;
import com.focusings.focusingsworld.features.home.mainchannel.usecase.GetRecentVideosFromChannelUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class MainChannelFragmentModule {

    @Provides
    MainChannelPresenter provideMainChannelPresenter(GetRecentVideosFromChannelUseCase getYoutubeVideosFromChannelUseCase) {
        return new MainChannelPresenter(getYoutubeVideosFromChannelUseCase);
    }

    @Provides
    GetRecentVideosFromChannelUseCase provideGetRecentVideosFromChannel(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            RecentVideosRepository recentVideosRepository) {
        return new GetRecentVideosFromChannelUseCase(threadExecutor, postExecutionThread, recentVideosRepository);
    }

}
