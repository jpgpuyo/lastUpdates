package com.focusings.focusingsworld.features.splash.initapp;

import com.focusings.focusingsworld.core.connectivity.Network;
import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.core.interactor.UseCase;
import com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data.RecentVideosRepository;
import com.focusings.focusingsworld.models.YoutubeChannel;

import rx.Observable;

public class InitApp extends UseCase {

    private final Network network;

    private final RecentVideosRepository recentVideosRepository;

    public InitApp(ThreadExecutor threadExecutor,
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
