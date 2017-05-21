package com.focusings.focusingsworld.repository;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.focusings.focusingsworld.bo.YoutubeVideo;
import com.focusings.focusingsworld.utils.prefs.PrefsCache;
import com.focusings.focusingsworld.repository.youtube.cache.PrefsCacheFactory;
import com.focusings.focusingsworld.repository.youtube.remote.YoutubeRemoteDataStore;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

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
        if (recentVideosCache.exists()) {
            return Observable.just(recentVideosCache.get());
        }
        return youtubeRemoteDataStore.getRecentVideosFromChannel(channelId)
                .doOnNext(new Action1<List<YoutubeVideo>>() {
                    @Override
                    public void call(List<YoutubeVideo> youtubeVideoList) {
                        recentVideosCache.put(youtubeVideoList);
                    }
                });
    }
}
