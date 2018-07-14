package com.focusings.focusingsworld.features.splash.usecase;

import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.core.interactor.UseCase;
import com.focusings.focusingsworld.core.utils.network.NetworkUtils;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosRepository;
import com.focusings.focusingsworld.data.youtube.models.YoutubeChannel;

import java.util.List;

import rx.Observable;

public class InitAppUseCase extends UseCase<List<YoutubeVideo>, Void> {

    private final NetworkUtils networkUtils;

    private final RecentVideosRepository recentVideosRepository;

    public InitAppUseCase(ThreadExecutor threadExecutor,
                          PostExecutionThread postExecutionThread,
                          NetworkUtils networkUtils,
                          RecentVideosRepository recentVideosRepository) {
        super(threadExecutor, postExecutionThread);
        this.networkUtils = networkUtils;
        this.recentVideosRepository = recentVideosRepository;
    }


    @Override
    protected Observable<List<YoutubeVideo>> buildUseCaseObservable(Void unused) {
        if (networkUtils.isNetworkAvailable()) {
            return recentVideosRepository.refreshVideos(YoutubeChannel.FEATURED_CHANNEL_ID);
        } else {
            return Observable.empty();
        }
    }
}
