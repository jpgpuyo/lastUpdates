package com.focusings.focusingsworld.features.splash.presenter;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.focusings.focusingsworld.core.interactor.DefaultSubscriber;
import com.focusings.focusingsworld.core.interactor.UseCase;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.features.splash.view.SplashView;

import java.util.List;

public class SplashAppPresenter {

    private final UseCase initAppUseCase;

    private SplashView splashView;

    public SplashAppPresenter(UseCase initAppUseCase) {
        this.initAppUseCase = initAppUseCase;
    }


    public void execute() {
        initAppUseCase.execute(new InitAppSubscriber(), null);
    }

    public void setView(SplashView splashView) {
        this.splashView = splashView;
    }

    private final class InitAppSubscriber extends DefaultSubscriber<List<YoutubeVideo>> {

        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            splashView.onInitAppFinished();
        }

        @Override
        public void onNext(List<YoutubeVideo> youtubeVideos) {
            super.onNext(youtubeVideos);
        }
    }
}
