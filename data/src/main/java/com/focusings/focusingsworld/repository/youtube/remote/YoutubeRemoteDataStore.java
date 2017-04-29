package com.focusings.focusingsworld.repository.youtube.remote;

import com.focusings.focusingsworld.bo.YoutubeVideo;
import com.focusings.focusingsworld.constants.YoutubeApiConstants;
import com.focusings.focusingsworld.repository.youtube.recentvideos.request.RecentVideosResponseDto;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class YoutubeRemoteDataStore {

    private final YoutubeService youtubeService;

    public YoutubeRemoteDataStore(YoutubeService youtubeService) {
        this.youtubeService = youtubeService;
    }

    public Observable<List<YoutubeVideo>> getRecentVideosFromChannel(String channelId) {
        return youtubeService.getRecentVideosFromChannel(channelId, YoutubeApiConstants.API_KEY)
                .map(new Func1<RecentVideosResponseDto, List<YoutubeVideo>>() {
                    @Override
                    public List<YoutubeVideo> call(RecentVideosResponseDto recentVideosResponseDto) {
                        return YoutubeDataParser.parse(recentVideosResponseDto);
                    }
                });
    }
}
