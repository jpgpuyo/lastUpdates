package com.focusings.focusingsworld.domain.interactors.init;

import com.focusings.focusingsworld.domain.models.YoutubeChannel;
import com.focusings.focusingsworld.domain.repository.YoutubeRepository;
import com.jpuyo.android.infrastructure.connectivity.Network;
import com.jpuyo.android.infrastructure.executor.PostExecutionThread;
import com.jpuyo.android.infrastructure.executor.ThreadExecutor;
import com.jpuyo.android.infrastructure.interactor.UseCase;

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
