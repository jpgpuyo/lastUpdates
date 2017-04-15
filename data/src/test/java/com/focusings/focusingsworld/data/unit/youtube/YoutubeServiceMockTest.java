package com.focusings.focusingsworld.data.unit.youtube;

import com.focusings.focusingsworld.data.unit.youtube.infrastructure.ApiClientTest;
import com.focusings.focusingsworld.repository.youtube.recentvideos.request.RecentVideosResponseDto;
import com.focusings.focusingsworld.repository.youtube.remote.YoutubeService;

import org.junit.Test;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.observers.TestSubscriber;

/**
 * Created by usuario on 14/04/2017.
 */
public class YoutubeServiceMockTest extends ApiClientTest {

    private static final String ANY_CHANNEL_ID = "1234";
    private static final String ANY_API_KEY = "abcd";

    @Test
    public void the_request_get_recent_videos_from_channel_should_contain_channel_id_and_api_key_as_params() throws Exception {
        YoutubeService youtubeService = givenYoutubeService();
        enqueueMockResponse();

        youtubeService.getRecentVideosFromChannel(ANY_CHANNEL_ID, ANY_API_KEY).subscribe(new TestSubscriber<>());

        assertRequestSentToContains("channelId=" + ANY_CHANNEL_ID, "key=" + ANY_API_KEY);
    }

    @Test public void should_parse_get_recent_videos_from_channel_response() throws Exception {
        YoutubeService youtubeService = givenYoutubeService();
        enqueueMockResponse("getRecentVideosFromChannel.json");

        TestSubscriber<RecentVideosResponseDto> subscriber = new TestSubscriber<>();

        youtubeService.getRecentVideosFromChannel(ANY_CHANNEL_ID, ANY_API_KEY).subscribe(subscriber);
    }


    private YoutubeService givenYoutubeService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseEndpoint())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(YoutubeService.class);
    }
}