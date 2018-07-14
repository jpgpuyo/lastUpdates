package com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data;

import com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data.datasources.RecentVideosCloud;
import com.focusings.focusingsworld.models.YoutubeVideo;

import java.util.List;

import rx.Observable;

public class RecentVideosRepository {

    private final RecentVideosCloud recentVideosCloud;

    private final MemoryDataStore memoryDataStore;

    public RecentVideosRepository(RecentVideosCloud recentVideosCloud,
                                  MemoryDataStore memoryRecentVideosDataStore) {
        this.recentVideosCloud = recentVideosCloud;
        this.memoryDataStore = memoryRecentVideosDataStore;
    }

    public Observable<List<YoutubeVideo>> refreshVideos(String channelId) {
        return recentVideosCloud.getRecentVideosFromChannel(channelId)
                .map(RecentVideosDataMapper::transform)
                .doOnNext(youtubeVideoList -> memoryDataStore.saveRecentVideosFromChannel(youtubeVideoList));
    }

    public Observable<List<YoutubeVideo>> getVideos() {
        return Observable.just(memoryDataStore.getRecentVideosFromChannel());
    }
}
