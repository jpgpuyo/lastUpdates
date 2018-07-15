package com.focusings.focusingsworld.unit.data.youtube.api;

import com.focusings.focusingsworld.data.youtube.core.api.YoutubeApi;
import com.focusings.focusingsworld.data.youtube.core.api.dto.SnippetDto;
import com.focusings.focusingsworld.data.youtube.core.api.dto.ThumbnailDto;
import com.focusings.focusingsworld.data.youtube.core.api.dto.YoutubeVideoDto;
import com.focusings.focusingsworld.data.youtube.core.api.dto.recentvideos.RecentVideosResponseDto;
import com.focusings.focusingsworld.utils.mockwebserver.TestApiClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class YoutubeApiTest extends TestApiClient {

    private static final String ANY_CHANNEL_ID = "1234";
    private static final String ANY_API_KEY = "abcd";

    @Test
    public void the_request_should_contain_channel_id_and_api_key_as_params() throws Exception {
        YoutubeApi youtubeApi = givenYoutubeApi(this);
        enqueueMockResponse();

        youtubeApi.getRecentVideosFromChannel(ANY_CHANNEL_ID, ANY_API_KEY).subscribe(new TestSubscriber<>());

        assertRequestSentToContains("channelId=" + ANY_CHANNEL_ID, "key=" + ANY_API_KEY);
    }

    @Test
    public void the_response_should_be_properly_parsed() throws Exception {
        YoutubeApi youtubeApi = givenYoutubeApi(this);
        enqueueMockResponse("getRecentVideosFromChannel.json");

        RecentVideosResponseDto recentVideosResponseDto =
                youtubeApi.getRecentVideosFromChannel(ANY_CHANNEL_ID, ANY_API_KEY)
                        .toBlocking().first();

        assertThat("youtube#searchListResponse").isEqualTo(recentVideosResponseDto.getKind());
        assertThat(5).isEqualTo(recentVideosResponseDto.getItems().size());
    }

    @Test
    public void first_video_from_response_should_be_properly_transformed_to_dto() throws Exception {
        YoutubeApi youtubeApi = givenYoutubeApi(this);
        enqueueMockResponse("getRecentVideosFromChannel.json");

        RecentVideosResponseDto recentVideosResponseDto =
                youtubeApi.getRecentVideosFromChannel(ANY_CHANNEL_ID, ANY_API_KEY)
                        .toBlocking().first();

        assertFirstVideoIsProperlyTransformedToDto(recentVideosResponseDto);
    }

    private void assertFirstVideoIsProperlyTransformedToDto(RecentVideosResponseDto recentVideosResponseDto) {
        List<YoutubeVideoDto> youtubeVideoDtoList = recentVideosResponseDto.getItems();
        YoutubeVideoDto firstVideo = youtubeVideoDtoList.get(0);
        assertThat(firstVideo.getId().getVideoId()).isEqualTo("yA2aXLboWBM");

        SnippetDto snippetDto = firstVideo.getSnippet();
        assertThat(snippetDto.getPublishedAt()).isEqualTo("2017-04-14T15:00:08.000Z");
        assertThat(snippetDto.getChannelId()).isEqualTo("UCNyRuOgL3IZ-smtoB3xeMbQ");
        assertThat(snippetDto.getTitle()).isEqualTo("Es hora de hablar claro");
        assertThat(snippetDto.getDescription()).isEqualTo("LINKS DE HILOS Y TWEETS HABLANDO SOBRE LA BISEXUALIDAD Y LA PANSEXUALIDAD: https://twitter.com/WintxrsWeather/status/848123225817194496 ...");
        assertThat(snippetDto.getChannelTitle()).isEqualTo("focusingsvlogs");
        assertThat(snippetDto.getLiveBroadcastContent()).isEqualTo("none");

        ThumbnailDto defaultThumbnail = snippetDto.getThumbnails().getDefaultThumbnail();
        ThumbnailDto mediumThumbnail = snippetDto.getThumbnails().getMediumThumbnail();
        ThumbnailDto highThumbnail = snippetDto.getThumbnails().getHighThumbnail();

        assertThat("https://i.ytimg.com/vi/yA2aXLboWBM/default.jpg").isEqualTo(defaultThumbnail.getUrl());
        assertThat(120).isEqualTo(defaultThumbnail.getWidth());
        assertThat(90).isEqualTo(defaultThumbnail.getHeight());

        assertThat("https://i.ytimg.com/vi/yA2aXLboWBM/mqdefault.jpg").isEqualTo(mediumThumbnail.getUrl());
        assertThat(320).isEqualTo(mediumThumbnail.getWidth());
        assertThat(180).isEqualTo(mediumThumbnail.getHeight());

        assertThat("https://i.ytimg.com/vi/yA2aXLboWBM/hqdefault.jpg").isEqualTo(highThumbnail.getUrl());
        assertThat(480).isEqualTo(highThumbnail.getWidth());
        assertThat(360).isEqualTo(highThumbnail.getHeight());
    }

    public static YoutubeApi givenYoutubeApi(TestApiClient testApiClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(testApiClient.getBaseEndpoint())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(YoutubeApi.class);
    }
}