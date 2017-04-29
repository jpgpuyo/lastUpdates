package com.focusings.focusingsworld.repository.youtube.remote;



import com.focusings.focusingsworld.repository.youtube.recentvideos.request.RecentVideosResponseDto;
import com.focusings.focusingsworld.repository.youtube.remote.dto.YoutubeVideoDto;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by usuario on 02/04/2017.
 */

public interface YoutubeService {

    @GET("search?part=snippet&maxResults=5&order=date")
    Observable<RecentVideosResponseDto> getRecentVideosFromChannel(@Query("channelId") String channelId, @Query("key") String key);
}
