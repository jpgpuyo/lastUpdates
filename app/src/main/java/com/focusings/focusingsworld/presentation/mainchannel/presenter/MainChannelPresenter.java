package com.focusings.focusingsworld.presentation.mainchannel.presenter;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.focusings.focusingsworld.domain.models.YoutubeVideo;
import com.focusings.focusingsworld.infrastructure.interactor.DefaultSubscriber;
import com.focusings.focusingsworld.infrastructure.interactor.UseCase;
import com.focusings.focusingsworld.presentation.mainchannel.model.YoutubeVideoModel;
import com.focusings.focusingsworld.presentation.mainchannel.view.MainChannelView;

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

    public void destroy() {
        getYoutubeVideosFromChannelUseCase.unsubscribe();
    }

    @RxLogSubscriber
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
            List<YoutubeVideoModel> youtubeVideoModelCollection = new YoutubeVideoModelMapper().transform(youtubeVideoCollection);
            mainChannelView.renderYoutubeVideoList(youtubeVideoModelCollection);
        }
    }
}
