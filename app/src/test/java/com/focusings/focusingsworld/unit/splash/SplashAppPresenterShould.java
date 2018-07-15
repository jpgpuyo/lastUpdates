package com.focusings.focusingsworld.unit.splash;


import com.focusings.focusingsworld.core.interactor.UseCase;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.features.splash.presenter.SplashAppPresenter;
import com.focusings.focusingsworld.features.splash.view.SplashView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SplashAppPresenterShould {

    SplashAppPresenter splashAppPresenter;

    @Mock
    UseCase initAppUseCase;

    @Mock
    SplashView splashView;

    @Before
    public void setUp() {
        splashAppPresenter = new SplashAppPresenter(initAppUseCase);
        splashAppPresenter.setView(splashView);
    }

    @Test public void unsubscribe_all_uses_cases_when_presenter_is_destroyed() {

        splashAppPresenter.destroy();

        verify(initAppUseCase, times(1)).unsubscribe();
    }

    @Test
    public void call_init_app_finished_when_init_app_success() {
        doAnswer(invocation -> {
            ((Subscriber) invocation.getArgument(0)).onNext(givenYoutubeVideoList());
            ((Subscriber) invocation.getArgument(0)).onCompleted();
            return null;
        }).when(initAppUseCase).execute(any(Subscriber.class), any());

        splashAppPresenter.execute();

        verify(splashView, times(1)).onInitAppFinished();
    }

    @Test
    public void call_init_app_finished_when_init_app_fails() {
        doAnswer(invocation -> {
            ((Subscriber) invocation.getArgument(0)).onError(new Exception());
            return null;
        }).when(initAppUseCase).execute(any(Subscriber.class), any());

        splashAppPresenter.execute();

        verify(splashView, times(1)).onInitAppFinished();
    }

    private List<YoutubeVideo> givenYoutubeVideoList() {
        List<YoutubeVideo> youtubeVideoList = new ArrayList<>();
        return youtubeVideoList;
    }
}
