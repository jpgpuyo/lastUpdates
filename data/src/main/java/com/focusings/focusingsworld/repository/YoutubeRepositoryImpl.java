package com.focusings.focusingsworld.repository;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.focusings.focusingsworld.bo.YoutubeVideo;
import com.focusings.focusingsworld.repository.youtube.local.YoutubeLocalDataStore;
import com.focusings.focusingsworld.repository.youtube.remote.YoutubeRemoteDataStore;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by usuario on 14/08/2016.
 */
public class YoutubeRepositoryImpl implements YoutubeRepository {

    private final YoutubeRemoteDataStore youtubeRemoteDataStore;

    private final YoutubeLocalDataStore youtubeLocalDataStore;

    public YoutubeRepositoryImpl(YoutubeRemoteDataStore youtubeRemoteDataStore,
                                 YoutubeLocalDataStore youtubeLocalDataStore) {
        this.youtubeRemoteDataStore = youtubeRemoteDataStore;
        this.youtubeLocalDataStore = youtubeLocalDataStore;
    }

    @RxLogObservable(RxLogObservable.Scope.SCHEDULERS)
    @Override
    public Observable<List<YoutubeVideo>> getRecentVideosFromChannel(String channelId) {
        if (!youtubeLocalDataStore.isEmpty()) {
            return youtubeLocalDataStore.get();
        }
        return youtubeRemoteDataStore.getRecentVideosFromChannel(channelId)
                .doOnNext(new Action1<List<YoutubeVideo>>() {
                    @Override
                    public void call(List<YoutubeVideo> youtubeVideoList) {
                        youtubeLocalDataStore.put(youtubeVideoList);
                    }
                });
    }
}
