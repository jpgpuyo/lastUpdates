package com.focusings.focusingsworld.features.home.mainchannel.recentvideos;

import com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data.RecentVideosRepository;
import com.focusings.focusingsworld.models.YoutubeChannel;
import com.jpuyo.android.infrastructure.connectivity.Network;
import com.jpuyo.android.infrastructure.executor.PostExecutionThread;
import com.jpuyo.android.infrastructure.executor.ThreadExecutor;
import com.jpuyo.android.infrastructure.interactor.UseCase;

import rx.Observable;
import rx.Subscriber;

public class GetRecentVideosFromChannel extends UseCase {

    private final Network network;

    private final RecentVideosRepository recentVideosRepository;

    private GetRecentVideosRequest getRecentVideosRequest;

    public GetRecentVideosFromChannel(ThreadExecutor threadExecutor,
                                      PostExecutionThread postExecutionThread,
                                      Network network,
                                      RecentVideosRepository recentVideosRepository) {
        super(threadExecutor, postExecutionThread);
        this.network = network;
        this.recentVideosRepository = recentVideosRepository;
    }

    public void execute(Subscriber useCaseSubscriber,
                        GetRecentVideosRequest getRecentVideosRequest) {
        this.getRecentVideosRequest = getRecentVideosRequest;
        super.execute(useCaseSubscriber);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        if (getRecentVideosRequest.isRefresh() && network.isAvailable()) {
            return recentVideosRepository.refreshVideos(YoutubeChannel.FEATURED_CHANNEL_ID)
                    .doOnTerminate(recentVideosRepository::getVideos);
        }
        return recentVideosRepository.getVideos();
    }
}
