package com.focusings.focusingsworld.unit.data.youtube.recentvideos;

import com.focusings.focusingsworld.data.youtube.core.api.YoutubeApi;
import com.focusings.focusingsworld.data.youtube.recentvideos.CloudRecentVideosDataStore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CloudRecentVideosDataStoreShould {

    private static final String ANY_CHANNEL_ID = "1234";
    private static final String ANY_API_KEY = "abcd";

    @Mock
    YoutubeApi youtubeApi;

    CloudRecentVideosDataStore cloudRecentVideosDataStore;

    @Before
    public void setUp() {
        this.cloudRecentVideosDataStore = new CloudRecentVideosDataStore(youtubeApi);
    }

    @Test
    public void transform_cloud_response_to_youtube_video_list() throws IOException {
        cloudRecentVideosDataStore.getRecentVideosFromChannel(ANY_CHANNEL_ID);

        verify(youtubeApi, times(1)).getRecentVideosFromChannel(ANY_CHANNEL_ID, ANY_API_KEY);
    }
}