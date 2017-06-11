package com.focusings.focusingsworld.presentation.mainchannel.presenter;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.focusings.focusingsworld.domain.interactors.GetRecentVideosFromChannel;
import com.focusings.focusingsworld.domain.interactors.GetRecentVideosRequest;
import com.focusings.focusingsworld.domain.models.YoutubeVideo;
import com.focusings.focusingsworld.infrastructure.interactor.DefaultSubscriber;
import com.focusings.focusingsworld.presentation.mainchannel.model.YoutubeVideoModel;
import com.focusings.focusingsworld.presentation.mainchannel.view.MainChannelView;

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

        List<YoutubeVideoModel> youtubeVideoModelList;

        public GetYoutubeVideosFromChannelSubscriber() {
            this.youtubeVideoModelList = new ArrayList<>();
        }

        @Override
        public void onCompleted() {
            super.onCompleted();
            mainChannelView.hideLoading();
            if (youtubeVideoModelList.isEmpty()) {
                mainChannelView.showNetworkError();
            }
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mainChannelView.hideLoading();
        }

        @Override
        public void onNext(List<YoutubeVideo> youtubeVideoCollection) {
            super.onNext(youtubeVideoCollection);
            youtubeVideoModelList = new YoutubeVideoModelMapper().transform(youtubeVideoCollection);
            mainChannelView.renderYoutubeVideoList(youtubeVideoModelList);
        }
    }
}
