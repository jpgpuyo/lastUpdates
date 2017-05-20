package com.focusings.focusingsworld.repository;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.focusings.focusingsworld.bo.YoutubeVideo;
import com.focusings.focusingsworld.repository.youtube.memory.RecentVideosCache;
import com.focusings.focusingsworld.repository.youtube.remote.YoutubeRemoteDataStore;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by usuario on 14/08/2016.
 */
public class YoutubeRepositoryImpl implements YoutubeRepository {

    private final YoutubeRemoteDataStore youtubeRemoteDataStore;

    private final RecentVideosCache recentVideosCache;

    public YoutubeRepositoryImpl(YoutubeRemoteDataStore youtubeRemoteDataStore,
                                 RecentVideosCache recentVideosCache) {
        this.youtubeRemoteDataStore = youtubeRemoteDataStore;
        this.recentVideosCache = recentVideosCache;
    }

    @RxLogObservable(RxLogObservable.Scope.SCHEDULERS)
    @Override
    public Observable<List<YoutubeVideo>> getRecentVideosFromChannel(String channelId) {
        if (!recentVideosCache.isEmpty()) {
            return recentVideosCache.get();
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
