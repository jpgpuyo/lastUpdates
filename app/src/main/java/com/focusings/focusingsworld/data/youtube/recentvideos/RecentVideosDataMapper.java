package com.focusings.focusingsworld.data.youtube.recentvideos;

import com.focusings.focusingsworld.data.youtube.core.api.dto.SnippetDto;
import com.focusings.focusingsworld.data.youtube.core.api.dto.ThumbnailsDto;
import com.focusings.focusingsworld.data.youtube.core.api.dto.recentvideos.RecentVideosResponseDto;
import com.focusings.focusingsworld.data.youtube.core.api.dto.IdDto;
import com.focusings.focusingsworld.data.youtube.core.api.dto.ThumbnailDto;
import com.focusings.focusingsworld.data.youtube.core.api.dto.YoutubeVideoDto;
import com.focusings.focusingsworld.data.youtube.models.Thumbnail;
import com.focusings.focusingsworld.data.youtube.models.Thumbnails;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;

import java.util.ArrayList;
import java.util.List;

public final class RecentVideosDataMapper {

    private static final String YOUTUBE_WATCH_VIDEO_URL = "https://www.youtube.com/watch?v=";

    private RecentVideosDataMapper() {
    }

    public static List<YoutubeVideo> transform(RecentVideosResponseDto recentVideosResponseDto) {
        List<YoutubeVideo> youtubeVideoList = new ArrayList<>();
        if (recentVideosResponseDto != null) {
            youtubeVideoList = transform(recentVideosResponseDto.getItems());
        }
        return youtubeVideoList;
    }

    private static List<YoutubeVideo> transform(List<YoutubeVideoDto> youtubeVideoDtoList) {
        List<YoutubeVideo> youtubeVideoList = new ArrayList<>();
        for (YoutubeVideoDto youtubeVideoDto : youtubeVideoDtoList) {
            YoutubeVideo youtubeVideo = transform(youtubeVideoDto);
            if (youtubeVideo != null) {
                youtubeVideoList.add(youtubeVideo);
            }
        }
        return youtubeVideoList;
    }

    private static YoutubeVideo transform(YoutubeVideoDto youtubeVideoDto) {
        YoutubeVideo youtubeVideo = null;
        if (youtubeVideoDto != null) {
            youtubeVideo = new YoutubeVideo();
            youtubeVideo.setUrl(transformUrl(youtubeVideoDto));
            youtubeVideo.setTitle(transformTitle(youtubeVideoDto));
            youtubeVideo.setImage(transformImage(youtubeVideoDto));
            youtubeVideo.setThumbnails(transformThumbnails(youtubeVideoDto));
        }
        return youtubeVideo;
    }

    private static String transformUrl(YoutubeVideoDto youtubeVideoDto) {
        StringBuilder url = new StringBuilder();
        IdDto idDto = youtubeVideoDto.getId();
        if (idDto != null) {
            String videoId = idDto.getVideoId();
            if (videoId != null) {
                url.append(YOUTUBE_WATCH_VIDEO_URL);
                url.append(videoId);
            }
        }
        return url.toString();
    }

    private static String transformTitle(YoutubeVideoDto youtubeVideoDto) {
        StringBuilder title = new StringBuilder();
        SnippetDto snippetDto = youtubeVideoDto.getSnippet();
        if (snippetDto != null) {
            title.append(snippetDto.getTitle());
        }
        return title.toString();
    }

    private static String transformImage(YoutubeVideoDto youtubeVideoDto) {
        StringBuilder image = new StringBuilder();
        SnippetDto snippetDto = youtubeVideoDto.getSnippet();
        if (snippetDto != null) {
            ThumbnailsDto thumbnailsDto = snippetDto.getThumbnails();
            if (thumbnailsDto != null) {
                ThumbnailDto mediumThumbnail = thumbnailsDto.getMediumThumbnail();
                if (mediumThumbnail != null) {
                    image.append(mediumThumbnail.getUrl());
                }
            }
        }
        return image.toString();
    }

    private static Thumbnails transformThumbnails(YoutubeVideoDto youtubeVideoDto) {
        Thumbnails thumbnails = null;
        SnippetDto snippetDto = youtubeVideoDto.getSnippet();
        if (snippetDto != null) {
            ThumbnailsDto thumbnailsDto = snippetDto.getThumbnails();
            if (thumbnailsDto != null) {
                thumbnails = new Thumbnails();
                Thumbnail defaultThumbnail = transformThumbnail(thumbnailsDto.getDefaultThumbnail());
                Thumbnail mediumThumbnail = transformThumbnail(thumbnailsDto.getMediumThumbnail());
                Thumbnail highThumbnail = transformThumbnail(thumbnailsDto.getHighThumbnail());

                if (defaultThumbnail != null) {
                    thumbnails.setDefaultThumbnail(defaultThumbnail);
                }

                if (mediumThumbnail != null) {
                    thumbnails.setMediumThumbnail(mediumThumbnail);
                }

                if (highThumbnail != null) {
                    thumbnails.setHighThumbnail(highThumbnail);
                }
            }
        }
        return thumbnails;
    }

    private static Thumbnail transformThumbnail(ThumbnailDto thumbnailDto) {
        Thumbnail thumbnail = null;
        if (thumbnailDto != null) {
            thumbnail = new Thumbnail();
            thumbnail.setWidth(thumbnailDto.getWidth());
            thumbnail.setHeight(thumbnailDto.getHeight());
            thumbnail.setUrl(thumbnailDto.getUrl());
        }
        return thumbnail;
    }
}
