package com.focusings.focusingsworld.features.home.mainchannel.usecase;

import com.focusings.focusingsworld.core.connectivity.Network;
import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.core.interactor.UseCase;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosRepository;
import com.focusings.focusingsworld.data.youtube.models.YoutubeChannel;

import rx.Observable;
import rx.Subscriber;

public class GetRecentVideosFromChannelUseCase extends UseCase {

    private final Network network;

    private final RecentVideosRepository recentVideosRepository;

    private GetRecentVideosRequest getRecentVideosRequest;

    public GetRecentVideosFromChannelUseCase(ThreadExecutor threadExecutor,
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
