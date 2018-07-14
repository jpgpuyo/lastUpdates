package com.focusings.focusingsworld.features.home.mainchannel.usecase;

import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.core.interactor.UseCase;
import com.focusings.focusingsworld.core.utils.network.NetworkUtils;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosRepository;
import com.focusings.focusingsworld.data.youtube.models.YoutubeChannel;

import java.util.List;

import rx.Observable;

public class GetRecentVideosFromChannelUseCase extends UseCase<List<YoutubeVideo>, GetRecentVideosFromChannelUseCase.Params> {

    private final NetworkUtils networkUtils;

    private final RecentVideosRepository recentVideosRepository;

    public GetRecentVideosFromChannelUseCase(ThreadExecutor threadExecutor,
                                             PostExecutionThread postExecutionThread,
                                             NetworkUtils networkUtils,
                                             RecentVideosRepository recentVideosRepository) {
        super(threadExecutor, postExecutionThread);
        this.networkUtils = networkUtils;
        this.recentVideosRepository = recentVideosRepository;
    }

    @Override
    protected Observable<List<YoutubeVideo>> buildUseCaseObservable(Params params) {
        if (params.isRefresh() && networkUtils.isNetworkAvailable()) {
            return recentVideosRepository.refreshVideos(YoutubeChannel.FEATURED_CHANNEL_ID)
                    .doOnTerminate(recentVideosRepository::getVideos);
        }
        return recentVideosRepository.getVideos();
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
