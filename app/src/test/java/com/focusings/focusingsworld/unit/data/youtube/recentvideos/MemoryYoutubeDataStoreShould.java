package com.focusings.focusingsworld.unit.data.youtube.recentvideos;

import com.focusings.focusingsworld.data.youtube.core.memory.MemoryYoutubeDataStore;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.utils.objectmother.RecentVideosMother;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MemoryYoutubeDataStoreShould {

    MemoryYoutubeDataStore memoryYoutubeDataStore;

    @Before
    public void setUp() {
        this.memoryYoutubeDataStore = new MemoryYoutubeDataStore();
    }

    @Test
    public void save_and_retrieve_recent_videos() throws IOException {
        List<YoutubeVideo> youtubeVideoList = RecentVideosMother.givenYoutubeVideoListFromDomain();

        memoryYoutubeDataStore.saveRecentVideosFromChannel(youtubeVideoList);
        List<YoutubeVideo> videoListRetrieved = memoryYoutubeDataStore.getRecentVideosFromChannel().toBlocking().first();

        assertThat(videoListRetrieved).isEqualTo(youtubeVideoList);
    }
}