package com.focusings.focusingsworld.repository;

import com.focusings.focusingsworld.bo.ImageAndText;

import java.util.List;

import rx.Observable;

/**
 * Created by usuario on 14/08/2016.
 */
public interface YoutubeRepository {
    Observable<List<ImageAndText>> getLastVideosFromChannel();
}
