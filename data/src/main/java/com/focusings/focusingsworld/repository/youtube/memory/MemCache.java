package com.focusings.focusingsworld.repository.youtube.memory;

import com.focusings.focusingsworld.bo.YoutubeVideo;

import java.util.List;

import rx.Observable;

/**
 * Created by usuario on 30/04/2017.
 */

public class MemCache<D> {

    private D data;

    public MemCache() {
    }

    public void put(D data) {
        this.data = data;
    }

    public Observable<D> get() {
        return Observable.just(data);
    }

    public boolean isEmpty() {
        return data == null;
    }
}
