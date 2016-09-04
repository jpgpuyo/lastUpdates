package com.focusings.focusingsworld.mainchannel;

import com.focusings.focusingsworld.bo.YoutubeVideo;
import com.focusings.focusingsworld.interactor.DefaultSubscriber;
import com.focusings.focusingsworld.interactor.UseCase;
import com.focusings.focusingsworld.mainchannel.mapper.YoutubeVideoModelDataMapper;
import com.focusings.focusingsworld.mainchannel.model.YoutubeVideoModel;

import java.util.List;

public class MainChannelPresenter {

    private final UseCase getYoutubeVideosFromChannelUseCase;
    private MainChannelView mainChannelView;

    public MainChannelPresenter(UseCase getYoutubeVideosFromChannelUseCase) {
        this.getYoutubeVideosFromChannelUseCase = getYoutubeVideosFromChannelUseCase;
    }

    public void setView(MainChannelView mainChannelView) {
        this.mainChannelView = mainChannelView;
    }

    public void getLastVideosFromYoutubeChannel(){
        getYoutubeVideosFromChannelUseCase.execute(new GetYoutubeVideosFromChannelSubscriber());
    }

    private final class GetYoutubeVideosFromChannelSubscriber extends DefaultSubscriber<List<YoutubeVideo>> {

        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<YoutubeVideo> youtubeVideoCollection) {
            super.onNext(youtubeVideoCollection);
            List<YoutubeVideoModel> youtubeVideoModelCollection = new YoutubeVideoModelDataMapper().transform(youtubeVideoCollection);
            mainChannelView.renderYoutubeVideoList(youtubeVideoModelCollection);
        }
    }
}
