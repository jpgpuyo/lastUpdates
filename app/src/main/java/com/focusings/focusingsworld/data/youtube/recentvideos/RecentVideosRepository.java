package com.focusings.focusingsworld.data.youtube.recentvideos;

import com.focusings.focusingsworld.data.youtube.core.memory.MemoryYoutubeDataStore;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Completable;
import rx.Observable;

public class RecentVideosRepository {

    private final CloudRecentVideosDataStore cloudRecentVideosDataStore;

    private final MemoryYoutubeDataStore memoryYoutubeDataStore;

    public RecentVideosRepository(CloudRecentVideosDataStore cloudRecentVideosDataStore,
                                  MemoryYoutubeDataStore memoryRecentVideosDataStore) {
        this.cloudRecentVideosDataStore = cloudRecentVideosDataStore;
        this.memoryYoutubeDataStore = memoryRecentVideosDataStore;
    }

    public Observable<List<YoutubeVideo>> getVideos(boolean refresh, String channelId) {
        return Completable.defer(() -> {
            if (refresh) {
                return fetchNetworkRecentVideos(channelId).timeout(10, TimeUnit.SECONDS);
            } else {
                return Completable.complete();
            }
        }).andThen(memoryYoutubeDataStore.getRecentVideosFromChannel());
    }

    private Completable fetchNetworkRecentVideos(String channelId) {
        return cloudRecentVideosDataStore.getRecentVideosFromChannel(channelId)
                .map(RecentVideosDataMapper::transform)
                .doOnNext(youtubeVideoList -> memoryYoutubeDataStore.saveRecentVideosFromChannel(youtubeVideoList))
                .toCompletable();
    }
}
