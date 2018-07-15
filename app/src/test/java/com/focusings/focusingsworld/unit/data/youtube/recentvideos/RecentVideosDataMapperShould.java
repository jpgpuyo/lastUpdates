package com.focusings.focusingsworld.unit.data.youtube.recentvideos;

import com.focusings.focusingsworld.data.youtube.core.api.dto.recentvideos.RecentVideosResponseDto;
import com.focusings.focusingsworld.data.youtube.models.Thumbnail;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosDataMapper;
import com.focusings.focusingsworld.utils.objectmother.RecentVideosMother;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RecentVideosDataMapperShould {

    @Test
    public void transform_cloud_response_to_youtube_video_list() throws IOException {
        List<YoutubeVideo> youtubeVideoList = RecentVideosDataMapper.transform(cloudResponse());

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

    private RecentVideosResponseDto cloudResponse() throws IOException {
        return RecentVideosMother.givenRecentVideosResponseDtoFromJson();
    }

    private List<YoutubeVideo> videoListInMemory() throws IOException {
        return RecentVideosMother.givenYoutubeVideoListFromDomain();
    }

}