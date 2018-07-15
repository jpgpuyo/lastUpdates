package com.focusings.focusingsworld.data.youtube.core.memory;

import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class MemoryYoutubeDataStore {

    List<YoutubeVideo> recentVideosList = new ArrayList<>();

    public void saveRecentVideosFromChannel(List<YoutubeVideo> recentVideosList) {
        this.recentVideosList = recentVideosList;
    }

    public Observable<List<YoutubeVideo>> getRecentVideosFromChannel() {
        return Observable.defer(() -> Observable.just(recentVideosList));
    }
}
