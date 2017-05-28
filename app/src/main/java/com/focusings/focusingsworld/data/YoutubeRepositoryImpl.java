package com.focusings.focusingsworld.data;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.focusings.focusingsworld.infrastructure.preferences.PrefsCache;
import com.focusings.focusingsworld.data.youtube.cache.PrefsCacheFactory;
import com.focusings.focusingsworld.data.youtube.remote.YoutubeRemoteDataStore;
import com.focusings.focusingsworld.domain.models.YoutubeVideo;
import com.focusings.focusingsworld.domain.repository.YoutubeRepository;


import java.util.List;

import rx.Observable;

/**
 * Created by usuario on 14/08/2016.
 */
public class YoutubeRepositoryImpl implements YoutubeRepository {

    private final YoutubeRemoteDataStore youtubeRemoteDataStore;

    private final PrefsCacheFactory prefsCacheFactory;

    public YoutubeRepositoryImpl(YoutubeRemoteDataStore youtubeRemoteDataStore,
                                 PrefsCacheFactory prefsCacheFactory) {
        this.youtubeRemoteDataStore = youtubeRemoteDataStore;
        this.prefsCacheFactory = prefsCacheFactory;
    }

    @RxLogObservable(RxLogObservable.Scope.SCHEDULERS)
    @Override
    public Observable<List<YoutubeVideo>> getRecentVideosFromChannel(String channelId) {
        final PrefsCache<List<YoutubeVideo>> recentVideosCache = prefsCacheFactory.get(PrefsCacheFactory.RECENT_VIDEOS);
        return Observable
                .concat(recentVideosCache.get(),
                        youtubeRemoteDataStore.getRecentVideosFromChannel(channelId))
                .first();

    }
}
