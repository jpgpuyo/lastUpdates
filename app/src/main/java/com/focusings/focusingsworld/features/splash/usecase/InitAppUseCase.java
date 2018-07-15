package com.focusings.focusingsworld.features.splash.usecase;

import android.support.annotation.NonNull;

import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.core.interactor.UseCase;
import com.focusings.focusingsworld.data.youtube.models.YoutubeChannel;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
        return recentVideosRepository.getVideos(true, YoutubeChannel.FEATURED_CHANNEL_ID)
               .compose(listObservable -> applyDelayForShowSplasAtLeastOneSecond(listObservable));
    }

    @NonNull
    private Observable<List<YoutubeVideo>> applyDelayForShowSplasAtLeastOneSecond(Observable<List<YoutubeVideo>> listObservable) {
        return listObservable.delay(1, TimeUnit.SECONDS);
    }
}
