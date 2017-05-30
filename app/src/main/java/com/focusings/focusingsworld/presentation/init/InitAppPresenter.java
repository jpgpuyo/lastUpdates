package com.focusings.focusingsworld.presentation.init;

import com.focusings.focusingsworld.infrastructure.interactor.DefaultSubscriber;
import com.focusings.focusingsworld.infrastructure.interactor.UseCase;

public class InitAppPresenter {

    private final UseCase initAppUseCase;

    public InitAppPresenter(UseCase initAppUseCase) {
        this.initAppUseCase = initAppUseCase;
    }

    public void execute(){
        initAppUseCase.execute(new DefaultSubscriber());
    }
}
