package com.focusings.focusingsworld.data.youtube.remote;

import com.focusings.focusingsworld.data.youtube.cache.PrefsCacheFactory;
import com.focusings.focusingsworld.domain.models.YoutubeVideo;

import java.util.List;

import rx.Observable;

public class YoutubeRemoteDataStore {

    private final YoutubeService youtubeService;

    private final PrefsCacheFactory prefsCacheFactory;

    public YoutubeRemoteDataStore(YoutubeService youtubeService, PrefsCacheFactory prefsCacheFactory) {
        this.youtubeService = youtubeService;
        this.prefsCacheFactory = prefsCacheFactory;
    }

    public Observable<List<YoutubeVideo>> getRecentVideosFromChannel(String channelId) {
        return youtubeService.getRecentVideosFromChannel(channelId, YoutubeApiConstants.API_KEY)
                .map(YoutubeDataParser::parse)
                .doOnNext(youtubeVideoList -> prefsCacheFactory.get(PrefsCacheFactory.RECENT_VIDEOS).put(youtubeVideoList));
    }
}
