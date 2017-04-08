package com.focusings.focusingsworld.repository.youtube.recentvideos.request;

import com.focusings.focusingsworld.bo.Thumbnail;
import com.focusings.focusingsworld.bo.Thumbnails;
import com.focusings.focusingsworld.bo.YoutubeVideo;
import com.focusings.focusingsworld.repository.youtube.remote.dto.IdDto;
import com.focusings.focusingsworld.repository.youtube.remote.dto.SnippetDto;
import com.focusings.focusingsworld.repository.youtube.remote.dto.ThumbnailDto;
import com.focusings.focusingsworld.repository.youtube.remote.dto.ThumbnailsDto;
import com.focusings.focusingsworld.repository.youtube.remote.dto.YoutubeVideoDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 04/04/2017.
 */

public class RecentVideosResponseDataMapper {

    public static List<YoutubeVideo> transform(RecentVideosResponseDto recentVideosResponseDto) {
        List<YoutubeVideo> youtubeVideoList = new ArrayList<>();
        if (recentVideosResponseDto != null) {
            List<YoutubeVideoDto> youtubeVideoDtoList = recentVideosResponseDto.getItems();
            if (youtubeVideoDtoList != null) {
                for (YoutubeVideoDto youtubeVideoDto : youtubeVideoDtoList) {
                    IdDto idDto = youtubeVideoDto.getId();
                    SnippetDto snippetDto = youtubeVideoDto.getSnippet();
                    if (idDto != null && snippetDto != null) {
                        YoutubeVideo youtubeVideo = new YoutubeVideo();
                        youtubeVideo.setUrl(idDto.getVideoId());
                        youtubeVideo.setTitle(snippetDto.getTitle());
                        youtubeVideo.setThumbnails(transformThumbnails(snippetDto.getThumbnails()));
                    }
                }
            }
        }
        return youtubeVideoList;
    }

    private static Thumbnails transformThumbnails(ThumbnailsDto thumbnailsDto) {
        Thumbnails thumbnails = null;
        if (thumbnailsDto != null) {
            thumbnails = new Thumbnails();
            thumbnails.setDefaultThumbnail(transformThumbnail(thumbnailsDto.getDefaultThumbnail()));
            thumbnails.setDefaultThumbnail(transformThumbnail(thumbnailsDto.getMediumThumbnail()));
            thumbnails.setDefaultThumbnail(transformThumbnail(thumbnailsDto.getHighThumbnail()));
        }
        return thumbnails;
    }

    private static Thumbnail transformThumbnail(ThumbnailDto thumbnailDto) {
        Thumbnail thumbnail = null;
        if (thumbnailDto != null) {
            thumbnail = new Thumbnail();
            thumbnail.setUrl(thumbnailDto.getUrl());
            thumbnail.setWidth(thumbnailDto.getWidth());
            thumbnail.setHeight(thumbnailDto.getHeight());
        }
        return thumbnail;
    }
}
