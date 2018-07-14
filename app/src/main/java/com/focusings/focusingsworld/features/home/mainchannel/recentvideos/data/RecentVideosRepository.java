package com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data;

import com.focusings.focusingsworld.core.preferences.PrefsCache;
import com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data.datasources.PrefsCacheFactory;
import com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data.datasources.RecentVideosCloud;
import com.focusings.focusingsworld.models.YoutubeVideo;

import java.util.List;

import rx.Observable;

public class RecentVideosRepository {

    private final RecentVideosCloud recentVideosCloud;

    private final PrefsCacheFactory prefsCacheFactory;

    public RecentVideosRepository(RecentVideosCloud recentVideosCloud,
                                  PrefsCacheFactory prefsCacheFactory) {
        this.recentVideosCloud = recentVideosCloud;
        this.prefsCacheFactory = prefsCacheFactory;
    }

    public Observable<List<YoutubeVideo>> refreshVideos(String channelId) {
        return recentVideosCloud.getRecentVideosFromChannel(channelId)
                .map(RecentVideosDataMapper::transform)
                .doOnNext(youtubeVideoList -> prefsCacheFactory.get(PrefsCacheFactory.RECENT_VIDEOS).put(youtubeVideoList));
    }

    public Observable<List<YoutubeVideo>> getVideos() {
        final PrefsCache<List<YoutubeVideo>> recentVideosCache = prefsCacheFactory.get(PrefsCacheFactory.RECENT_VIDEOS);
        return recentVideosCache.get();
    }
}
