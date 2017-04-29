package com.focusings.focusingsworld.interactor;

import com.focusings.focusingsworld.bo.YoutubeChannel;
import com.focusings.focusingsworld.executor.PostExecutionThread;
import com.focusings.focusingsworld.executor.ThreadExecutor;
import com.focusings.focusingsworld.repository.YoutubeRepository;

import rx.Observable;

/**
 * Created by usuario on 15/08/2016.
 */
public class GetYoutubeVideosFromChannelUseCase extends UseCase {

    private final YoutubeRepository youtubeRepository;

    public GetYoutubeVideosFromChannelUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, YoutubeRepository youtubeRepository) {
        super(threadExecutor, postExecutionThread);
        this.youtubeRepository = youtubeRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return youtubeRepository.getRecentVideosFromChannel(YoutubeChannel.FEATURED_CHANNEL_ID);
    }
}
