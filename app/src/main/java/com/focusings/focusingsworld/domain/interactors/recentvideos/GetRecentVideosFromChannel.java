package com.focusings.focusingsworld.domain.interactors.recentvideos;

import com.focusings.focusingsworld.domain.models.YoutubeChannel;
import com.focusings.focusingsworld.domain.repository.YoutubeRepository;
import com.focusings.focusingsworld.infrastructure.connectivity.Network;
import com.focusings.focusingsworld.infrastructure.executor.PostExecutionThread;
import com.focusings.focusingsworld.infrastructure.executor.ThreadExecutor;
import com.focusings.focusingsworld.infrastructure.interactor.UseCase;

import rx.Observable;
import rx.Subscriber;

public class GetRecentVideosFromChannel extends UseCase {

    private final YoutubeRepository youtubeRepository;

    private final Network network;

    private GetRecentVideosRequest getRecentVideosRequest;

    public GetRecentVideosFromChannel(ThreadExecutor threadExecutor,
                                      PostExecutionThread postExecutionThread,
                                      Network network,
                                      YoutubeRepository youtubeRepository) {
        super(threadExecutor, postExecutionThread);
        this.network = network;
        this.youtubeRepository = youtubeRepository;
    }

    public void execute(Subscriber useCaseSubscriber,
                        GetRecentVideosRequest getRecentVideosRequest) {
        this.getRecentVideosRequest = getRecentVideosRequest;
        super.execute(useCaseSubscriber);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        if (getRecentVideosRequest.isRefresh() && network.isAvailable()) {
            return youtubeRepository.refreshVideos(YoutubeChannel.FEATURED_CHANNEL_ID)
                    .doOnTerminate(youtubeRepository::getVideos);
        }
        return youtubeRepository.getVideos();
    }
}
