package com.focusings.focusingsworld.data.youtube.remote;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.focusings.focusingsworld.domain.models.YoutubeVideo;

import java.util.List;

import rx.Observable;

public class YoutubeRemoteDataStore {

    private final YoutubeService youtubeService;

    public YoutubeRemoteDataStore(YoutubeService youtubeService) {
        this.youtubeService = youtubeService;
    }

    @RxLogObservable(RxLogObservable.Scope.SCHEDULERS)
    public Observable<List<YoutubeVideo>> getRecentVideosFromChannel(String channelId) {
        return youtubeService.getRecentVideosFromChannel(channelId, YoutubeApiConstants.API_KEY)
                .map(YoutubeDataParser::parse);
    }
}
