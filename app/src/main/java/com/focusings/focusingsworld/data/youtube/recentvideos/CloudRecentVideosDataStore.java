package com.focusings.focusingsworld.data.youtube.recentvideos;

import com.focusings.focusingsworld.data.youtube.core.api.YoutubeApi;
import com.focusings.focusingsworld.data.youtube.core.api.YoutubeApiConstants;
import com.focusings.focusingsworld.data.youtube.core.api.dto.recentvideos.RecentVideosResponseDto;

import rx.Observable;

public class CloudRecentVideosDataStore {

    private final YoutubeApi youtubeApi;

    public CloudRecentVideosDataStore(YoutubeApi youtubeApi) {
        this.youtubeApi = youtubeApi;
    }

    public Observable<RecentVideosResponseDto> getRecentVideosFromChannel(String channelId) {
        return youtubeApi.getRecentVideosFromChannel(channelId, YoutubeApiConstants.API_KEY);
    }
}
