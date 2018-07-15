package com.focusings.focusingsworld.unit.home.mainchannel;


import com.focusings.focusingsworld.core.interactor.UseCase;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosRepository;
import com.focusings.focusingsworld.features.home.mainchannel.usecase.GetRecentVideosFromChannelUseCase;

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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetRecentVideosFromChannelUseCaseShould extends TestUseCase {

    private UseCase getRecentVideosFromChannelUseCase;

    @Mock
    private RecentVideosRepository recentVideosRepository;

    @Before
    public void setUp() {
        super.setUp();
        this.getRecentVideosFromChannelUseCase =
                new GetRecentVideosFromChannelUseCase(threadExecutor, postExecutionThread, recentVideosRepository);
    }

    @Test
    public void get_recent_videos_with_refresh() throws IOException {
        List<YoutubeVideo> youtubeVideoList = RecentVideosMother.givenYoutubeVideoListFromDomain();
        when(recentVideosRepository.getVideos(eq(true), any())).thenReturn(Observable.just(youtubeVideoList));

        getRecentVideosFromChannelUseCase.execute(testSubscriber, GetRecentVideosFromChannelUseCase.Params.refresh(true));

        testSubscriber.awaitTerminalEvent();

        testSubscriber.assertValue(youtubeVideoList);
        testSubscriber.assertCompleted();
    }

    @Test
    public void get_recent_videos_with_no_refresh() throws IOException {
        List<YoutubeVideo> youtubeVideoList = RecentVideosMother.givenYoutubeVideoListFromDomain();
        when(recentVideosRepository.getVideos(eq(false), any())).thenReturn(Observable.just(youtubeVideoList));

        getRecentVideosFromChannelUseCase.execute(testSubscriber, GetRecentVideosFromChannelUseCase.Params.refresh(false));

        testSubscriber.awaitTerminalEvent();

        testSubscriber.assertValue(youtubeVideoList);
        testSubscriber.assertCompleted();
    }
}
