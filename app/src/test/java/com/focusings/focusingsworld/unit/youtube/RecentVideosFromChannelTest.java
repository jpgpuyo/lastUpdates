package com.focusings.focusingsworld.unit.youtube;

import com.focusings.focusingsworld.data.youtube.core.api.YoutubeApi;
import com.focusings.focusingsworld.data.youtube.core.api.dto.SnippetDto;
import com.focusings.focusingsworld.data.youtube.core.api.dto.ThumbnailDto;
import com.focusings.focusingsworld.data.youtube.core.api.dto.YoutubeVideoDto;
import com.focusings.focusingsworld.data.youtube.core.api.dto.recentvideos.RecentVideosResponseDto;
import com.focusings.focusingsworld.data.youtube.core.memory.MemoryYoutubeDataStore;
import com.focusings.focusingsworld.data.youtube.models.Thumbnail;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.data.youtube.recentvideos.CloudRecentVideosDataStore;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import rx.observers.TestSubscriber;
import com.focusings.focusingsworld.utils.mockwebserver.TestApiClient;
import com.focusings.focusingsworld.utils.objectmother.YoutubeTestMother;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RecentVideosFromChannelTest extends TestApiClient {

    private static final String ANY_CHANNEL_ID = "1234";
    private static final String ANY_API_KEY = "abcd";

    @Test
    public void the_request_should_contain_channel_id_and_api_key_as_params() throws Exception {
        YoutubeApi youtubeApi = YoutubeTestMother.givenYoutubeService(this);
        enqueueMockResponse();

        youtubeApi.getRecentVideosFromChannel(ANY_CHANNEL_ID, ANY_API_KEY).subscribe(new TestSubscriber<>());

        assertRequestSentToContains("channelId=" + ANY_CHANNEL_ID, "key=" + ANY_API_KEY);
    }

    @Test
    public void the_response_should_be_properly_parsed() throws Exception {
        YoutubeApi youtubeApi = YoutubeTestMother.givenYoutubeService(this);
        enqueueMockResponse("getRecentVideosFromChannel.json");

        RecentVideosResponseDto recentVideosResponseDto =
                youtubeApi.getRecentVideosFromChannel(ANY_CHANNEL_ID, ANY_API_KEY)
                        .toBlocking().first();

        assertThat("youtube#searchListResponse").isEqualTo(recentVideosResponseDto.getKind());
        assertThat(5).isEqualTo(recentVideosResponseDto.getItems().size());
    }

    @Test
    public void first_video_from_response_should_be_properly_transformed_to_dto() throws Exception {
        CloudRecentVideosDataStore cloudRecentVideosDataStore = YoutubeTestMother.givenYoutubeRemoteDataStore(this);
        enqueueMockResponse("getRecentVideosFromChannel.json");

        RecentVideosResponseDto recentVideosResponseDto =
                cloudRecentVideosDataStore.getRecentVideosFromChannel(ANY_CHANNEL_ID)
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

    @Test
    public void first_video_from_response_should_be_properly_transformed_to_bo() throws Exception {
        CloudRecentVideosDataStore cloudRecentVideosDataStore = YoutubeTestMother.givenYoutubeRemoteDataStore(this);
        enqueueMockResponse("getRecentVideosFromChannel.json");

        RecentVideosRepository recentVideosRepository = new RecentVideosRepository(cloudRecentVideosDataStore, new MemoryYoutubeDataStore());
        List<YoutubeVideo> youtubeVideoList =
                recentVideosRepository.getVideos(true, ANY_CHANNEL_ID)
                        .toBlocking().first();

        assertFirstVideoIsProperlyTransformedToBo(youtubeVideoList);
    }

    private void assertFirstVideoIsProperlyTransformedToBo(List<YoutubeVideo> youtubeVideoList) {
        YoutubeVideo youtubeVideo = youtubeVideoList.get(0);
        assertThat("Es hora de hablar claro").isEqualTo(youtubeVideo.getTitle());
        assertThat("https://www.youtube.com/watch?v=yA2aXLboWBM").isEqualTo(youtubeVideo.getUrl());
        assertThat("https://i.ytimg.com/vi/yA2aXLboWBM/mqdefault.jpg").isEqualTo(youtubeVideo.getImage());

        Thumbnail defaultThumbnail = youtubeVideo.getThumbnails().getDefaultThumbnail();
        Thumbnail mediumThumbnail = youtubeVideo.getThumbnails().getMediumThumbnail();
        Thumbnail highThumbnail = youtubeVideo.getThumbnails().getHighThumbnail();

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
}