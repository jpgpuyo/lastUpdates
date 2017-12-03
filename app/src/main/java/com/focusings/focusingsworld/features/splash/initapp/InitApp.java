package com.focusings.focusingsworld.features.splash.initapp;

import com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data.RecentVideosRepository;
import com.focusings.focusingsworld.models.YoutubeChannel;
import com.jpuyo.android.infrastructure.connectivity.Network;
import com.jpuyo.android.infrastructure.executor.PostExecutionThread;
import com.jpuyo.android.infrastructure.executor.ThreadExecutor;
import com.jpuyo.android.infrastructure.interactor.UseCase;

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
