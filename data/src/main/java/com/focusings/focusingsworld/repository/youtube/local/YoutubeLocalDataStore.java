package com.focusings.focusingsworld.repository.youtube.local;

import com.focusings.focusingsworld.bo.YoutubeVideo;

import java.util.List;

import rx.Observable;

public interface YoutubeLocalDataStore {
    void put(List<YoutubeVideo> youtubeVideoList);
    Observable<List<YoutubeVideo>> get();
    boolean isEmpty();
}
