package com.focusings.focusingsworld.repository.youtube.remote;

import com.focusings.focusingsworld.bo.YoutubeVideo;
import com.focusings.focusingsworld.constants.YoutubeApiConstants;
import com.focusings.focusingsworld.repository.youtube.cache.PrefsCacheFactory;
import com.focusings.focusingsworld.repository.youtube.recentvideos.request.RecentVideosResponseDto;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class YoutubeRemoteDataStore {

    private final YoutubeService youtubeService;

    private final PrefsCacheFactory prefsCacheFactory;

    public YoutubeRemoteDataStore(YoutubeService youtubeService, PrefsCacheFactory prefsCacheFactory) {
        this.youtubeService = youtubeService;
        this.prefsCacheFactory = prefsCacheFactory;
    }

    public Observable<List<YoutubeVideo>> getRecentVideosFromChannel(String channelId) {
        return youtubeService.getRecentVideosFromChannel(channelId, YoutubeApiConstants.API_KEY)
                .map(new Func1<RecentVideosResponseDto, List<YoutubeVideo>>() {
                    @Override
                    public List<YoutubeVideo> call(RecentVideosResponseDto recentVideosResponseDto) {
                        return YoutubeDataParser.parse(recentVideosResponseDto);
                    }
                }).doOnNext(new Action1<List<YoutubeVideo>>() {
                    @Override
                    public void call(List<YoutubeVideo> youtubeVideoList) {
                        prefsCacheFactory.get(PrefsCacheFactory.RECENT_VIDEOS).put(youtubeVideoList);
                    }
        });
    }
}
