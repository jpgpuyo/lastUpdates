package com.focusings.focusingsworld.features.home.mainchannel.usecase;

import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.core.interactor.UseCase;
import com.focusings.focusingsworld.data.youtube.models.YoutubeChannel;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosRepository;

import java.util.List;

import rx.Observable;

public class GetRecentVideosFromChannelUseCase extends UseCase<List<YoutubeVideo>, GetRecentVideosFromChannelUseCase.Params> {

    private final RecentVideosRepository recentVideosRepository;

    public GetRecentVideosFromChannelUseCase(ThreadExecutor threadExecutor,
                                             PostExecutionThread postExecutionThread,
                                             RecentVideosRepository recentVideosRepository) {
        super(threadExecutor, postExecutionThread);
        this.recentVideosRepository = recentVideosRepository;
    }

    @Override
    protected Observable<List<YoutubeVideo>> buildUseCaseObservable(Params params) {
        return recentVideosRepository.getVideos(params.refresh, YoutubeChannel.FEATURED_CHANNEL_ID);
    }

    public static final class Params {

        private boolean refresh;

        private Params(boolean refresh) {
            this.refresh = refresh;
        }

        public static Params refresh(boolean refresh) {
            return new Params(refresh);
        }

        public boolean isRefresh() {
            return refresh;
        }
    }
}
