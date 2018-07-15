package com.focusings.focusingsworld.data.youtube.core.memory;

import com.focusings.focusingsworld.core.utils.rx.RxUtils;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class MemoryYoutubeDataStore {

    List<YoutubeVideo> recentVideosFromChannel = new ArrayList<>();

    public void saveRecentVideosFromChannel(List<YoutubeVideo> recentVideosList) {
        this.recentVideosFromChannel = recentVideosList;
    }

    public Observable<List<YoutubeVideo>> getRecentVideosFromChannel() {
        return RxUtils.asObservable(recentVideosFromChannel);
    }
}
