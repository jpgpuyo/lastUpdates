package com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data.datasources;

import com.focusings.focusingsworld.data.youtube.api.YoutubeApi;
import com.focusings.focusingsworld.data.youtube.api.YoutubeApiConstants;
import com.focusings.focusingsworld.data.youtube.api.dto.recentvideos.RecentVideosResponseDto;

import rx.Observable;

public class RecentVideosCloud {

    private final YoutubeApi youtubeApi;

    public RecentVideosCloud(YoutubeApi youtubeApi) {
        this.youtubeApi = youtubeApi;
    }

    public Observable<RecentVideosResponseDto> getRecentVideosFromChannel(String channelId) {
        return youtubeApi.getRecentVideosFromChannel(channelId, YoutubeApiConstants.API_KEY);
    }
}
