package com.focusings.focusingsworld.repository.youtube.remote;

import com.focusings.focusingsworld.bo.Thumbnail;
import com.focusings.focusingsworld.bo.Thumbnails;
import com.focusings.focusingsworld.bo.YoutubeVideo;
import com.focusings.focusingsworld.repository.youtube.recentvideos.request.RecentVideosResponseDto;
import com.focusings.focusingsworld.repository.youtube.remote.dto.IdDto;
import com.focusings.focusingsworld.repository.youtube.remote.dto.SnippetDto;
import com.focusings.focusingsworld.repository.youtube.remote.dto.ThumbnailDto;
import com.focusings.focusingsworld.repository.youtube.remote.dto.ThumbnailsDto;
import com.focusings.focusingsworld.repository.youtube.remote.dto.YoutubeVideoDto;

import java.util.ArrayList;
import java.util.List;

public final class YoutubeDataParser {

    private static final String YOUTUBE_WATCH_VIDEO_URL = "https://www.youtube.com/watch?v=";

    private YoutubeDataParser() {
    }

    public static List<YoutubeVideo> parse(RecentVideosResponseDto recentVideosResponseDto) {
        List<YoutubeVideo> youtubeVideoList = new ArrayList<>();
        if (recentVideosResponseDto != null) {
            youtubeVideoList = parse(recentVideosResponseDto.getItems());
        }
        return youtubeVideoList;
    }

    private static List<YoutubeVideo> parse(List<YoutubeVideoDto> youtubeVideoDtoList) {
        List<YoutubeVideo> youtubeVideoList = new ArrayList<>();
        for (YoutubeVideoDto youtubeVideoDto : youtubeVideoDtoList) {
            YoutubeVideo youtubeVideo = parse(youtubeVideoDto);
            if (youtubeVideo != null) {
                youtubeVideoList.add(youtubeVideo);
            }
        }
        return youtubeVideoList;
    }

    private static YoutubeVideo parse(YoutubeVideoDto youtubeVideoDto) {
        YoutubeVideo youtubeVideo = null;
        if (youtubeVideoDto != null) {
            youtubeVideo = new YoutubeVideo();
            youtubeVideo.setUrl(parseUrl(youtubeVideoDto));
            youtubeVideo.setTitle(parseTitle(youtubeVideoDto));
            youtubeVideo.setImage(parseImage(youtubeVideoDto));
            youtubeVideo.setThumbnails(parseThumbnails(youtubeVideoDto));
        }
        return youtubeVideo;
    }

    private static String parseUrl(YoutubeVideoDto youtubeVideoDto) {
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

    private static String parseTitle(YoutubeVideoDto youtubeVideoDto) {
        StringBuilder title = new StringBuilder();
        SnippetDto snippetDto = youtubeVideoDto.getSnippet();
        if (snippetDto != null) {
            title.append(snippetDto.getTitle());
        }
        return title.toString();
    }

    private static String parseImage(YoutubeVideoDto youtubeVideoDto) {
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

    private static Thumbnails parseThumbnails(YoutubeVideoDto youtubeVideoDto) {
        Thumbnails thumbnails = null;
        SnippetDto snippetDto = youtubeVideoDto.getSnippet();
        if (snippetDto != null) {
            ThumbnailsDto thumbnailsDto = snippetDto.getThumbnails();
            if (thumbnailsDto != null) {
                thumbnails = new Thumbnails();
                Thumbnail defaultThumbnail = parseThumbnail(thumbnailsDto.getDefaultThumbnail());
                Thumbnail mediumThumbnail = parseThumbnail(thumbnailsDto.getMediumThumbnail());
                Thumbnail highThumbnail = parseThumbnail(thumbnailsDto.getHighThumbnail());

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

    private static Thumbnail parseThumbnail(ThumbnailDto thumbnailDto) {
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
