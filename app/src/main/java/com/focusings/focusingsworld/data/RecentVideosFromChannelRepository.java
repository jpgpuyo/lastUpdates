package com.focusings.focusingsworld.data;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.focusings.focusingsworld.data.youtube.cache.PrefsCacheFactory;
import com.focusings.focusingsworld.data.youtube.remote.YoutubeRemoteDataStore;
import com.focusings.focusingsworld.domain.models.YoutubeVideo;
import com.focusings.focusingsworld.domain.repository.YoutubeRepository;
import com.focusings.focusingsworld.infrastructure.preferences.PrefsCache;

import java.util.List;

import rx.Observable;

public class RecentVideosFromChannelRepository implements YoutubeRepository {

    private final YoutubeRemoteDataStore youtubeRemoteDataStore;

    private final PrefsCacheFactory prefsCacheFactory;

    public RecentVideosFromChannelRepository(YoutubeRemoteDataStore youtubeRemoteDataStore,
                                             PrefsCacheFactory prefsCacheFactory) {
        this.youtubeRemoteDataStore = youtubeRemoteDataStore;
        this.prefsCacheFactory = prefsCacheFactory;
    }

    @RxLogObservable(RxLogObservable.Scope.SCHEDULERS)
    @Override
    public Observable<Void> refreshVideos(String channelId) {
        youtubeRemoteDataStore.getRecentVideosFromChannel(channelId)
                .doOnNext(youtubeVideoList -> prefsCacheFactory.get(PrefsCacheFactory.RECENT_VIDEOS).put(youtubeVideoList));
        return Observable.empty();
    }

    @RxLogObservable(RxLogObservable.Scope.SCHEDULERS)
    @Override
    public Observable<List<YoutubeVideo>> getVideos() {
        final PrefsCache<List<YoutubeVideo>> recentVideosCache = prefsCacheFactory.get(PrefsCacheFactory.RECENT_VIDEOS);
        return recentVideosCache.get();
    }
}
