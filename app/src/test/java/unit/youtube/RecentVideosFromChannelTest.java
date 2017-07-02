package unit.youtube;

import com.focusings.focusingsworld.data.youtube.api.YoutubeApi;
import com.focusings.focusingsworld.data.youtube.api.dto.SnippetDto;
import com.focusings.focusingsworld.data.youtube.api.dto.ThumbnailDto;
import com.focusings.focusingsworld.data.youtube.api.dto.YoutubeVideoDto;
import com.focusings.focusingsworld.data.youtube.api.dto.recentvideos.RecentVideosResponseDto;
import com.focusings.focusingsworld.data.youtube.cache.PrefsCacheFactory;
import com.focusings.focusingsworld.data.youtube.repository.recentvideos.RecentVideosRepository;
import com.focusings.focusingsworld.data.youtube.repository.recentvideos.datasources.RecentVideosCloud;
import com.focusings.focusingsworld.domain.models.Thumbnail;
import com.focusings.focusingsworld.domain.models.YoutubeVideo;
import com.focusings.focusingsworld.infrastructure.preferences.PrefsCache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import rx.observers.TestSubscriber;
import unit.youtube.infrastructure.ApiClientTest;
import unit.youtube.infrastructure.YoutubeTestMother;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RecentVideosFromChannelTest extends ApiClientTest {

    @Mock
    PrefsCacheFactory prefsCacheFactory;

    @Mock
    PrefsCache<List<YoutubeVideo>> cacheRecentVideos;

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

        assertEquals("youtube#searchListResponse", recentVideosResponseDto.getKind());
        assertEquals(5, recentVideosResponseDto.getItems().size());
    }

    @Test
    public void first_video_from_response_should_be_properly_transformed_to_dto() throws Exception {
        RecentVideosCloud recentVideosCloud = YoutubeTestMother.givenYoutubeRemoteDataStore(this);
        enqueueMockResponse("getRecentVideosFromChannel.json");

        RecentVideosResponseDto recentVideosResponseDto =
                recentVideosCloud.getRecentVideosFromChannel(ANY_CHANNEL_ID)
                        .toBlocking().first();

        assertFirstVideoIsProperlyTransformedToDto(recentVideosResponseDto);
    }

    private void assertFirstVideoIsProperlyTransformedToDto(RecentVideosResponseDto recentVideosResponseDto) {
        List<YoutubeVideoDto> youtubeVideoDtoList = recentVideosResponseDto.getItems();
        YoutubeVideoDto firstVideo = youtubeVideoDtoList.get(0);
        assertEquals(firstVideo.getId().getVideoId(), "yA2aXLboWBM");

        SnippetDto snippetDto = firstVideo.getSnippet();
        assertEquals(snippetDto.getPublishedAt(), "2017-04-14T15:00:08.000Z");
        assertEquals(snippetDto.getChannelId(), "UCNyRuOgL3IZ-smtoB3xeMbQ");
        assertEquals(snippetDto.getTitle(), "Es hora de hablar claro");
        assertEquals(snippetDto.getDescription(), "LINKS DE HILOS Y TWEETS HABLANDO SOBRE LA BISEXUALIDAD Y LA PANSEXUALIDAD: https://twitter.com/WintxrsWeather/status/848123225817194496 ...");
        assertEquals(snippetDto.getChannelTitle(), "focusingsvlogs");
        assertEquals(snippetDto.getLiveBroadcastContent(), "none");

        ThumbnailDto defaultThumbnail = snippetDto.getThumbnails().getDefaultThumbnail();
        ThumbnailDto mediumThumbnail = snippetDto.getThumbnails().getMediumThumbnail();
        ThumbnailDto highThumbnail = snippetDto.getThumbnails().getHighThumbnail();

        assertEquals("https://i.ytimg.com/vi/yA2aXLboWBM/default.jpg", defaultThumbnail.getUrl());
        assertEquals(120, (long) defaultThumbnail.getWidth());
        assertEquals(90, (long) defaultThumbnail.getHeight());

        assertEquals("https://i.ytimg.com/vi/yA2aXLboWBM/mqdefault.jpg", mediumThumbnail.getUrl());
        assertEquals(320, (long) mediumThumbnail.getWidth());
        assertEquals(180, (long) mediumThumbnail.getHeight());

        assertEquals("https://i.ytimg.com/vi/yA2aXLboWBM/hqdefault.jpg", highThumbnail.getUrl());
        assertEquals(480, (long) highThumbnail.getWidth());
        assertEquals(360, (long) highThumbnail.getHeight());
    }

    @Test
    public void first_video_from_response_should_be_properly_transformed_to_bo() throws Exception {
        RecentVideosCloud recentVideosCloud = YoutubeTestMother.givenYoutubeRemoteDataStore(this);
        enqueueMockResponse("getRecentVideosFromChannel.json");

        when(prefsCacheFactory.get(anyString())).thenReturn(cacheRecentVideos);

        RecentVideosRepository recentVideosRepository = new RecentVideosRepository(recentVideosCloud, prefsCacheFactory);
        List<YoutubeVideo> youtubeVideoList =
                recentVideosRepository.refreshVideos(ANY_CHANNEL_ID)
                        .toBlocking().first();

        assertFirstVideoIsProperlyTransformedToBo(youtubeVideoList);
    }

    private void assertFirstVideoIsProperlyTransformedToBo(List<YoutubeVideo> youtubeVideoList) {
        YoutubeVideo youtubeVideo = youtubeVideoList.get(0);
        assertEquals("Es hora de hablar claro", youtubeVideo.getTitle());
        assertEquals("https://www.youtube.com/watch?v=yA2aXLboWBM", youtubeVideo.getUrl());
        assertEquals("https://i.ytimg.com/vi/yA2aXLboWBM/mqdefault.jpg", youtubeVideo.getImage());

        Thumbnail defaultThumbnail = youtubeVideo.getThumbnails().getDefaultThumbnail();
        Thumbnail mediumThumbnail = youtubeVideo.getThumbnails().getMediumThumbnail();
        Thumbnail highThumbnail = youtubeVideo.getThumbnails().getHighThumbnail();

        assertEquals("https://i.ytimg.com/vi/yA2aXLboWBM/default.jpg", defaultThumbnail.getUrl());
        assertEquals(120, defaultThumbnail.getWidth());
        assertEquals(90, defaultThumbnail.getHeight());

        assertEquals("https://i.ytimg.com/vi/yA2aXLboWBM/mqdefault.jpg", mediumThumbnail.getUrl());
        assertEquals(320, mediumThumbnail.getWidth());
        assertEquals(180, mediumThumbnail.getHeight());

        assertEquals("https://i.ytimg.com/vi/yA2aXLboWBM/hqdefault.jpg", highThumbnail.getUrl());
        assertEquals(480, highThumbnail.getWidth());
        assertEquals(360, highThumbnail.getHeight());
    }
}