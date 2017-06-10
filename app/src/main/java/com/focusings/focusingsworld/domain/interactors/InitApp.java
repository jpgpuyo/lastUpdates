package com.focusings.focusingsworld.domain.interactors;

import com.focusings.focusingsworld.domain.models.YoutubeChannel;
import com.focusings.focusingsworld.domain.repository.YoutubeRepository;
import com.focusings.focusingsworld.infrastructure.connectivity.Network;
import com.focusings.focusingsworld.infrastructure.executor.PostExecutionThread;
import com.focusings.focusingsworld.infrastructure.executor.ThreadExecutor;
import com.focusings.focusingsworld.infrastructure.interactor.UseCase;

import rx.Observable;

public class InitApp extends UseCase {

    private final Network network;

    private final YoutubeRepository youtubeRepository;

    public InitApp(ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread,
                   Network network,
                   YoutubeRepository youtubeRepository) {
        super(threadExecutor, postExecutionThread);
        this.network = network;
        this.youtubeRepository = youtubeRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        if (network.isAvailable()) {
            return youtubeRepository.refreshVideos(YoutubeChannel.FEATURED_CHANNEL_ID);
        } else {
            return Observable.empty();
        }
    }
}
