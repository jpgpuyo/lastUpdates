package com.focusings.focusingsworld.domain.repository;


import com.focusings.focusingsworld.domain.models.YoutubeVideo;

import java.util.List;

import rx.Observable;

/**
 * Created by usuario on 14/08/2016.
 */
public interface YoutubeRepository {
    Observable<List<YoutubeVideo>> getRecentVideosFromChannel(String channelId);
}
