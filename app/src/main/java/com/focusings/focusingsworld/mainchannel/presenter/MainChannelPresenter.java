package com.focusings.focusingsworld.mainchannel.presenter;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.focusings.focusingsworld.bo.YoutubeVideo;
import com.focusings.focusingsworld.dagger.PerActivity;
import com.focusings.focusingsworld.interactor.DefaultSubscriber;
import com.focusings.focusingsworld.interactor.UseCase;
import com.focusings.focusingsworld.mainchannel.model.YoutubeVideoModel;
import com.focusings.focusingsworld.mainchannel.view.MainChannelView;

import java.util.List;

import javax.inject.Inject;

@PerActivity
public class MainChannelPresenter {

    private final UseCase getYoutubeVideosFromChannelUseCase;
    private MainChannelView mainChannelView;

    @Inject
    public MainChannelPresenter(UseCase getYoutubeVideosFromChannelUseCase) {
        this.getYoutubeVideosFromChannelUseCase = getYoutubeVideosFromChannelUseCase;
    }

    public void setView(MainChannelView mainChannelView) {
        this.mainChannelView = mainChannelView;
    }

    public void getLastVideosFromYoutubeChannel(){
        getYoutubeVideosFromChannelUseCase.execute(new GetYoutubeVideosFromChannelSubscriber());
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
