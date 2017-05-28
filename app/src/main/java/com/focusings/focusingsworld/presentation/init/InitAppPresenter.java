package com.focusings.focusingsworld.presentation.init;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.focusings.focusingsworld.domain.models.YoutubeVideo;
import com.focusings.focusingsworld.infrastructure.interactor.DefaultSubscriber;
import com.focusings.focusingsworld.infrastructure.interactor.UseCase;

import java.util.List;

/**
 * Created by usuario on 22/05/2017.
 */

public class InitAppPresenter {

    private final UseCase initAppUseCase;

    public InitAppPresenter(UseCase initAppUseCase) {
        this.initAppUseCase = initAppUseCase;
    }

    public void execute(){
        initAppUseCase.execute(new InitAppPresenter.InitAppSubscriber());
    }

    @RxLogSubscriber
    private final class InitAppSubscriber extends DefaultSubscriber<List<YoutubeVideo>> {

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
        }
    }
}
