package com.focusings.focusingsworld.domain.interactors;

import com.focusings.focusingsworld.domain.models.YoutubeChannel;
import com.focusings.focusingsworld.domain.repository.YoutubeRepository;
import com.focusings.focusingsworld.infrastructure.executor.PostExecutionThread;
import com.focusings.focusingsworld.infrastructure.executor.ThreadExecutor;
import com.focusings.focusingsworld.infrastructure.interactor.UseCase;

import rx.Observable;

/**
 * Created by usuario on 15/08/2016.
 */
public class InitApp extends UseCase {

    private final YoutubeRepository youtubeRepository;

    public InitApp(ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread,
                   YoutubeRepository youtubeRepository) {
        super(threadExecutor, postExecutionThread);
        this.youtubeRepository = youtubeRepository;
    }

    @Override
    protected Observable<Void> buildUseCaseObservable() {
        return youtubeRepository.refreshVideos(YoutubeChannel.FEATURED_CHANNEL_ID);
    }
}
