package com.focusings.focusingsworld.repository;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.focusings.focusingsworld.bo.YoutubeVideo;
import com.focusings.focusingsworld.repository.youtube.remote.YoutubeRemoteDataStore;

import java.util.List;

import rx.Observable;

/**
 * Created by usuario on 14/08/2016.
 */
public class YoutubeRepositoryImpl implements YoutubeRepository {

    private final YoutubeRemoteDataStore youtubeRemoteDataStore;

    public YoutubeRepositoryImpl(YoutubeRemoteDataStore youtubeRemoteDataStore) {
        this.youtubeRemoteDataStore = youtubeRemoteDataStore;
    }

    @RxLogObservable(RxLogObservable.Scope.SCHEDULERS)
    @Override
    public Observable<List<YoutubeVideo>> getRecentVideosFromChannel(String channelId) {
        return youtubeRemoteDataStore.getRecentVideosFromChannel(channelId);
    }
}
