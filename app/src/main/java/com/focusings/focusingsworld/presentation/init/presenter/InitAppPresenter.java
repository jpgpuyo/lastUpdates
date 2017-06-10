package com.focusings.focusingsworld.presentation.init.presenter;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.focusings.focusingsworld.domain.models.YoutubeVideo;
import com.focusings.focusingsworld.infrastructure.interactor.DefaultSubscriber;
import com.focusings.focusingsworld.infrastructure.interactor.UseCase;
import com.focusings.focusingsworld.presentation.init.view.SplashView;

import java.util.List;

public class InitAppPresenter {

    private final UseCase initAppUseCase;

    private SplashView splashView;

    public InitAppPresenter(UseCase initAppUseCase) {
        this.initAppUseCase = initAppUseCase;
    }

    public void execute(){
        initAppUseCase.execute(new InitAppSubscriber());
    }

    public void setView(SplashView splashView) {
        this.splashView = splashView;
    }

    @RxLogSubscriber
    private final class InitAppSubscriber extends DefaultSubscriber<List<YoutubeVideo>> {

        @Override
        public void onCompleted() {
            super.onCompleted();
            splashView.onInitAppFinished();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<YoutubeVideo> youtubeVideos) {
            super.onNext(youtubeVideos);
            splashView.onInitAppFinished();
        }
    }
}
