package com.focusings.focusingsworld.data.youtube.api;



import com.focusings.focusingsworld.data.youtube.api.dto.recentvideos.RecentVideosResponseDto;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface YoutubeApi {

    @GET("search?part=snippet&maxResults=5&order=date")
    Observable<RecentVideosResponseDto> getRecentVideosFromChannel(@Query("channelId") String channelId, @Query("key") String key);
}
