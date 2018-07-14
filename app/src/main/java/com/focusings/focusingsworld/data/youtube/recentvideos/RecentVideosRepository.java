package com.focusings.focusingsworld.data.youtube.recentvideos;

import com.focusings.focusingsworld.data.youtube.core.memory.MemoryYoutubeDataStore;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;

import java.util.List;

import rx.Observable;

public class RecentVideosRepository {

    private final CloudRecentVideosDataStore cloudRecentVideosDataStore;

    private final MemoryYoutubeDataStore memoryYoutubeDataStore;

    public RecentVideosRepository(CloudRecentVideosDataStore cloudRecentVideosDataStore,
                                  MemoryYoutubeDataStore memoryRecentVideosDataStore) {
        this.cloudRecentVideosDataStore = cloudRecentVideosDataStore;
        this.memoryYoutubeDataStore = memoryRecentVideosDataStore;
    }

    public Observable<List<YoutubeVideo>> refreshVideos(String channelId) {
        return cloudRecentVideosDataStore.getRecentVideosFromChannel(channelId)
                .map(RecentVideosDataMapper::transform)
                .doOnNext(youtubeVideoList -> memoryYoutubeDataStore.saveRecentVideosFromChannel(youtubeVideoList));
    }

    public Observable<List<YoutubeVideo>> getVideos() {
        return Observable.just(memoryYoutubeDataStore.getRecentVideosFromChannel());
    }
}
