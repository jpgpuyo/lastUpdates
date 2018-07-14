package com.focusings.focusingsworld.features.home.mainchannel.presenter;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.focusings.focusingsworld.core.exception.DefaultErrorBundle;
import com.focusings.focusingsworld.core.interactor.DefaultSubscriber;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.features.home.mainchannel.usecase.GetRecentVideosFromChannelUseCase;
import com.focusings.focusingsworld.features.home.mainchannel.view.MainChannelView;

import java.util.ArrayList;
import java.util.List;

public class MainChannelPresenter {

    private final GetRecentVideosFromChannelUseCase getRecentVideosFromChannelUseCase;
    private MainChannelView mainChannelView;

    public MainChannelPresenter(GetRecentVideosFromChannelUseCase getRecentVideosFromChannelUseCase) {
        this.getRecentVideosFromChannelUseCase = getRecentVideosFromChannelUseCase;
    }

    public void setView(MainChannelView mainChannelView) {
        this.mainChannelView = mainChannelView;
    }

    public void getLastVideosFromYoutubeChannel(boolean refresh) {
        getRecentVideosFromChannelUseCase.execute(
                new GetYoutubeVideosFromChannelSubscriber(),
                GetRecentVideosFromChannelUseCase.Params.refresh(refresh));
    }

    public void destroy() {
        getRecentVideosFromChannelUseCase.unsubscribe();
    }

    @RxLogSubscriber
    private final class GetYoutubeVideosFromChannelSubscriber extends DefaultSubscriber<List<YoutubeVideo>> {

        List<YoutubeVideo> youtubeVideoList;

        public GetYoutubeVideosFromChannelSubscriber() {
            this.youtubeVideoList = new ArrayList<>();
        }

        @Override
        public void onCompleted() {
            super.onCompleted();
            mainChannelView.hideLoading();
            if (youtubeVideoList.isEmpty()) {
                mainChannelView.showNetworkError();
            }
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mainChannelView.hideLoading();
            mainChannelView.showError(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onNext(List<YoutubeVideo> youtubeVideoList) {
            super.onNext(youtubeVideoList);
            this.youtubeVideoList = youtubeVideoList;
            mainChannelView.renderYoutubeVideoList(youtubeVideoList);
        }
    }
}
