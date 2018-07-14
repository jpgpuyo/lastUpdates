package com.focusings.focusingsworld.features.splash.usecase;

import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.core.interactor.UseCase;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosRepository;
import com.focusings.focusingsworld.data.youtube.models.YoutubeChannel;

import java.util.List;

import rx.Observable;

public class InitAppUseCase extends UseCase<List<YoutubeVideo>, Void> {

    private final RecentVideosRepository recentVideosRepository;

    public InitAppUseCase(ThreadExecutor threadExecutor,
                          PostExecutionThread postExecutionThread,
                          RecentVideosRepository recentVideosRepository) {
        super(threadExecutor, postExecutionThread);
        this.recentVideosRepository = recentVideosRepository;
    }


    @Override
    protected Observable<List<YoutubeVideo>> buildUseCaseObservable(Void unused) {
        return recentVideosRepository.getVideos(true, YoutubeChannel.FEATURED_CHANNEL_ID);
    }
}
