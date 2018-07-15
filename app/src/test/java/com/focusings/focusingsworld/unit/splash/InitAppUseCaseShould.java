package com.focusings.focusingsworld.unit.splash;

import com.focusings.focusingsworld.core.interactor.UseCase;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosRepository;
import com.focusings.focusingsworld.features.splash.usecase.InitAppUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import com.focusings.focusingsworld.utils.objectmother.RecentVideosMother;
import com.focusings.focusingsworld.utils.usecase.TestUseCase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InitAppUseCaseShould extends TestUseCase {

    private UseCase initAppUseCase;

    @Mock
    private RecentVideosRepository recentVideosRepository;

    @Before
    public void setUp() {
        super.setUp();
        this.initAppUseCase =
                new InitAppUseCase(threadExecutor, postExecutionThread, recentVideosRepository);
    }

    @Test
    public void videos_are_retrieved_after_app_init() throws IOException {
        List<YoutubeVideo> youtubeVideoList = RecentVideosMother.givenYoutubeVideoListFromDomain();
        when(recentVideosRepository.getVideos(eq(true), any())).thenReturn(Observable.just(youtubeVideoList));

        initAppUseCase.execute(testSubscriber, null);

        testSubscriber.awaitTerminalEvent();

        testSubscriber.assertValue(youtubeVideoList);
        testSubscriber.assertCompleted();
        verify(recentVideosRepository, times(1)).getVideos(eq(true),any());
    }

    @Test
    public void always_call_repositories_with_param_refresh_true() throws IOException {
        List<YoutubeVideo> youtubeVideoList = RecentVideosMother.givenYoutubeVideoListFromDomain();
        when(recentVideosRepository.getVideos(eq(true), any())).thenReturn(Observable.just(youtubeVideoList));

        initAppUseCase.execute(testSubscriber, null);

        testSubscriber.awaitTerminalEvent();

        verify(recentVideosRepository, times(1)).getVideos(eq(true),any());
    }
}
