package com.focusings.focusingsworld.features.splash.usecase;

import com.focusings.focusingsworld.core.connectivity.Network;
import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.core.interactor.UseCase;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosRepository;
import com.focusings.focusingsworld.data.youtube.models.YoutubeChannel;

import rx.Observable;

public class InitAppUseCase extends UseCase {

    private final Network network;

    private final RecentVideosRepository recentVideosRepository;

    public InitAppUseCase(ThreadExecutor threadExecutor,
                          PostExecutionThread postExecutionThread,
                          Network network,
                          RecentVideosRepository recentVideosRepository) {
        super(threadExecutor, postExecutionThread);
        this.network = network;
        this.recentVideosRepository = recentVideosRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        if (network.isAvailable()) {
            return recentVideosRepository.refreshVideos(YoutubeChannel.FEATURED_CHANNEL_ID);
        } else {
            return Observable.empty();
        }
    }
}
