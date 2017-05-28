package unit.youtube;

import com.focusings.focusingsworld.data.youtube.recentvideos.request.RecentVideosResponseDto;
import com.focusings.focusingsworld.data.youtube.remote.YoutubeRemoteDataStore;
import com.focusings.focusingsworld.data.youtube.remote.YoutubeService;
import com.focusings.focusingsworld.domain.models.Thumbnail;
import com.focusings.focusingsworld.domain.models.YoutubeVideo;

import unit.youtube.infrastructure.ApiClientTest;
import unit.youtube.infrastructure.YoutubeTestMother;

import org.junit.Test;

import java.util.List;

import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;

/**
 * Created by usuario on 14/04/2017.
 */
public class RecentVideosFromChannelTest extends ApiClientTest {

    private static final String ANY_CHANNEL_ID = "1234";
    private static final String ANY_API_KEY = "abcd";

    @Test
    public void the_request_should_contain_channel_id_and_api_key_as_params() throws Exception {
        YoutubeService youtubeService = YoutubeTestMother.givenYoutubeService(this);
        enqueueMockResponse();

        youtubeService.getRecentVideosFromChannel(ANY_CHANNEL_ID, ANY_API_KEY).subscribe(new TestSubscriber<>());

        assertRequestSentToContains("channelId=" + ANY_CHANNEL_ID, "key=" + ANY_API_KEY);
    }

    @Test
    public void the_response_should_be_properly_parsed() throws Exception {
        YoutubeService youtubeService = YoutubeTestMother.givenYoutubeService(this);
        enqueueMockResponse("getRecentVideosFromChannel.json");

        RecentVideosResponseDto recentVideosResponseDto =
                youtubeService.getRecentVideosFromChannel(ANY_CHANNEL_ID, ANY_API_KEY)
                        .toBlocking().first();

        assertEquals("youtube#searchListResponse", recentVideosResponseDto.getKind());
        assertEquals(5, recentVideosResponseDto.getItems().size());
    }

    @Test
    public void first_video_from_response_should_be_properly_transformed_to_bo() throws Exception {
        YoutubeRemoteDataStore youtubeRemoteDataStore = YoutubeTestMother.givenYoutubeRemoteDataStore(this);
        enqueueMockResponse("getRecentVideosFromChannel.json");

        List<YoutubeVideo> youtubeVideoList =
                youtubeRemoteDataStore.getRecentVideosFromChannel(ANY_CHANNEL_ID)
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