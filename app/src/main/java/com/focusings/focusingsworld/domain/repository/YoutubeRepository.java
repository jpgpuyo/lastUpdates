package com.focusings.focusingsworld.domain.repository;


import com.focusings.focusingsworld.domain.models.YoutubeVideo;

import java.util.List;

import rx.Observable;

public interface YoutubeRepository {
    Observable<List<YoutubeVideo>> refreshVideos(String channelId);
    Observable<List<YoutubeVideo>> getVideos();
}
