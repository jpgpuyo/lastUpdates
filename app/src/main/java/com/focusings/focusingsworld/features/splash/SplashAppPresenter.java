package com.focusings.focusingsworld.features.splash;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.focusings.focusingsworld.models.YoutubeVideo;
import com.jpuyo.android.infrastructure.interactor.DefaultSubscriber;
import com.jpuyo.android.infrastructure.interactor.UseCase;

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
