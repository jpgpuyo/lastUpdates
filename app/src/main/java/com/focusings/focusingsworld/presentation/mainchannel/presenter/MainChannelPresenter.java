package com.focusings.focusingsworld.presentation.mainchannel.presenter;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.focusings.focusingsworld.domain.interactors.recentvideos.GetRecentVideosFromChannel;
import com.focusings.focusingsworld.domain.interactors.recentvideos.GetRecentVideosRequest;
import com.focusings.focusingsworld.domain.models.YoutubeVideo;
import com.focusings.focusingsworld.presentation.mainchannel.view.MainChannelView;
import com.jpuyo.android.infrastructure.interactor.DefaultSubscriber;

import java.util.ArrayList;
import java.util.List;

public class MainChannelPresenter {

    private final GetRecentVideosFromChannel getRecentVideosFromChannel;
    private MainChannelView mainChannelView;

    public MainChannelPresenter(GetRecentVideosFromChannel getRecentVideosFromChannel) {
        this.getRecentVideosFromChannel = getRecentVideosFromChannel;
    }

    public void setView(MainChannelView mainChannelView) {
        this.mainChannelView = mainChannelView;
    }

    public void getLastVideosFromYoutubeChannel(boolean refresh) {
        GetRecentVideosRequest getRecentVideosRequest = new GetRecentVideosRequest();
        getRecentVideosRequest.setRefresh(refresh);
        getRecentVideosFromChannel.execute(
                new GetYoutubeVideosFromChannelSubscriber(),
                getRecentVideosRequest);
    }

    public void destroy() {
        getRecentVideosFromChannel.unsubscribe();
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
        }

        @Override
        public void onNext(List<YoutubeVideo> youtubeVideoList) {
            super.onNext(youtubeVideoList);
            this.youtubeVideoList = youtubeVideoList;
            mainChannelView.renderYoutubeVideoList(youtubeVideoList);
        }
    }
}
