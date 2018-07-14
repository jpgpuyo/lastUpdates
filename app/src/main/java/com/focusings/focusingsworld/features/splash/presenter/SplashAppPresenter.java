package com.focusings.focusingsworld.features.splash.presenter;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.focusings.focusingsworld.core.interactor.DefaultSubscriber;
import com.focusings.focusingsworld.core.interactor.UseCase;
import com.focusings.focusingsworld.features.splash.view.SplashView;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;

import java.util.List;

public class SplashAppPresenter {

    private final UseCase initAppUseCase;

    private SplashView splashView;

    public SplashAppPresenter(UseCase initAppUseCase) {
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
