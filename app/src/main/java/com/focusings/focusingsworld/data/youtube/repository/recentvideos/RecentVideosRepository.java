package com.focusings.focusingsworld.data.youtube.repository.recentvideos;

import com.focusings.focusingsworld.data.youtube.cache.PrefsCacheFactory;
import com.focusings.focusingsworld.data.youtube.repository.recentvideos.datasources.RecentVideosCloud;
import com.focusings.focusingsworld.domain.models.YoutubeVideo;
import com.focusings.focusingsworld.domain.repository.YoutubeRepository;
import com.jpuyo.android.infrastructure.preferences.PrefsCache;

import java.util.List;

import rx.Observable;

public class RecentVideosRepository implements YoutubeRepository {

    private final RecentVideosCloud recentVideosCloud;

    private final PrefsCacheFactory prefsCacheFactory;

    public RecentVideosRepository(RecentVideosCloud recentVideosCloud,
                                  PrefsCacheFactory prefsCacheFactory) {
        this.recentVideosCloud = recentVideosCloud;
        this.prefsCacheFactory = prefsCacheFactory;
    }

    @Override
    public Observable<List<YoutubeVideo>> refreshVideos(String channelId) {
        return recentVideosCloud.getRecentVideosFromChannel(channelId)
                .map(RecentVideosDataMapper::transform)
                .doOnNext(youtubeVideoList -> prefsCacheFactory.get(PrefsCacheFactory.RECENT_VIDEOS).put(youtubeVideoList));
    }

    @Override
    public Observable<List<YoutubeVideo>> getVideos() {
        final PrefsCache<List<YoutubeVideo>> recentVideosCache = prefsCacheFactory.get(PrefsCacheFactory.RECENT_VIDEOS);
        return recentVideosCache.get();
    }
}
