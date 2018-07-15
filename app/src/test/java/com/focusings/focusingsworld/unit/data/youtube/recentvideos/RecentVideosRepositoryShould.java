package com.focusings.focusingsworld.unit.data.youtube.recentvideos;

import com.focusings.focusingsworld.data.youtube.core.api.dto.recentvideos.RecentVideosResponseDto;
import com.focusings.focusingsworld.data.youtube.core.memory.MemoryYoutubeDataStore;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.data.youtube.recentvideos.CloudRecentVideosDataStore;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosRepository;
import com.focusings.focusingsworld.utils.objectmother.RecentVideosMother;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import rx.Observable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecentVideosRepositoryShould {

    private static final String ANY_CHANNEL_ID = "1234";

    @Mock
    CloudRecentVideosDataStore cloudRecentVideosDataStore;

    @Mock
    MemoryYoutubeDataStore memoryYoutubeDataStore;

    RecentVideosRepository recentVideosRepository;

    @Before
    public void setUp() throws IOException {
        recentVideosRepository = new RecentVideosRepository(cloudRecentVideosDataStore, memoryYoutubeDataStore);

        RecentVideosResponseDto recentVideosResponseDto = cloudResponse();
        List<YoutubeVideo> youtubeVideoList = videoListInMemory();
        when(cloudRecentVideosDataStore.getRecentVideosFromChannel(ANY_CHANNEL_ID)).thenReturn(Observable.just(recentVideosResponseDto));
        when(memoryYoutubeDataStore.getRecentVideosFromChannel()).thenReturn(Observable.just(youtubeVideoList));

    }

    @Test
    public void get_videos_from_cloud_and_persist_when_refresh_true() throws IOException {
        List<YoutubeVideo> youtubeVideoList = recentVideosRepository.getVideos(true, ANY_CHANNEL_ID).toBlocking().first();

        verify(cloudRecentVideosDataStore, times(1)).getRecentVideosFromChannel(ANY_CHANNEL_ID);
        verify(memoryYoutubeDataStore, times(1)).saveRecentVideosFromChannel(anyList());
        verify(memoryYoutubeDataStore, times(1)).getRecentVideosFromChannel();
        assertThat(youtubeVideoList).isNotEmpty();
    }

    @Test
    public void get_videos_from_memory_when_refresh_false() throws IOException {
        List<YoutubeVideo> youtubeVideoList = recentVideosRepository.getVideos(false, ANY_CHANNEL_ID).toBlocking().first();

        verify(cloudRecentVideosDataStore, times(0)).getRecentVideosFromChannel(ANY_CHANNEL_ID);
        verify(memoryYoutubeDataStore, times(0)).saveRecentVideosFromChannel(anyList());
        verify(memoryYoutubeDataStore, times(1)).getRecentVideosFromChannel();
        assertThat(youtubeVideoList).isNotEmpty();

    }

    private RecentVideosResponseDto cloudResponse() throws IOException {
        return RecentVideosMother.givenRecentVideosResponseDtoFromJson();
    }

    private List<YoutubeVideo> videoListInMemory() throws IOException {
        return RecentVideosMother.givenYoutubeVideoListFromDomain();
    }

}