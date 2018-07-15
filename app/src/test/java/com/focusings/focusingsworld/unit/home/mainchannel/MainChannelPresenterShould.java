package com.focusings.focusingsworld.unit.home.mainchannel;


import com.focusings.focusingsworld.core.exception.DefaultErrorBundle;
import com.focusings.focusingsworld.core.interactor.UseCase;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.features.home.mainchannel.presenter.MainChannelPresenter;
import com.focusings.focusingsworld.features.home.mainchannel.view.MainChannelView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import rx.Subscriber;
import com.focusings.focusingsworld.utils.objectmother.RecentVideosMother;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainChannelPresenterShould {

    MainChannelPresenter mainChannelPresenter;

    @Mock
    UseCase getRecentVideosFromChannelUseCase;

    @Mock
    MainChannelView mainChannelView;

    @Before
    public void setUp() {
        mainChannelPresenter = new MainChannelPresenter(getRecentVideosFromChannelUseCase);
        mainChannelPresenter.setView(mainChannelView);
    }

    @Test
    public void unsubscribe_all_use_cases_when_presenter_is_destroyed() {

        mainChannelPresenter.destroy();

        verify(getRecentVideosFromChannelUseCase, times(1)).unsubscribe();
    }

    @Test
    public void get_videos_and_render_when_when_we_have_videos() throws IOException {
        List<YoutubeVideo> youtubeVideoList = RecentVideosMother.givenYoutubeVideoListFromDomain();
        doAnswer(invocation -> {
            ((Subscriber) invocation.getArgument(0)).onNext(youtubeVideoList);
            ((Subscriber) invocation.getArgument(0)).onCompleted();
            return null;
        }).when(getRecentVideosFromChannelUseCase).execute(any(Subscriber.class), any());

        mainChannelPresenter.getLastVideosFromYoutubeChannel(true);

        verify(mainChannelView, times(1)).renderYoutubeVideoList(youtubeVideoList);
        verify(mainChannelView, times(1)).hideLoading();
    }

    @Test
    public void get_videos_and_show_error_when_we_have_some_exception() {
        Exception exception = new Exception();
        doAnswer(invocation -> {
            ((Subscriber) invocation.getArgument(0)).onError(exception);
            return null;
        }).when(getRecentVideosFromChannelUseCase).execute(any(Subscriber.class), any());

        mainChannelPresenter.getLastVideosFromYoutubeChannel(true);

        verify(mainChannelView, times(1)).hideLoading();
        verify(mainChannelView, times(1)).showError(any(DefaultErrorBundle.class));
    }

    @Test
    public void get_videos_and_show_network_error_when_videos_are_not_found() {
        List<YoutubeVideo> youtubeVideoList = RecentVideosMother.givenEmptyYoutubeVideoListFromDomain();
        doAnswer(invocation -> {
            ((Subscriber) invocation.getArgument(0)).onNext(youtubeVideoList);
            ((Subscriber) invocation.getArgument(0)).onCompleted();
            return null;
        }).when(getRecentVideosFromChannelUseCase).execute(any(Subscriber.class), any());

        mainChannelPresenter.getLastVideosFromYoutubeChannel(true);

        verify(mainChannelView, times(0)).renderYoutubeVideoList(youtubeVideoList);
        verify(mainChannelView, times(1)).hideLoading();
        verify(mainChannelView, times(1)).showNetworkError();
    }
}
