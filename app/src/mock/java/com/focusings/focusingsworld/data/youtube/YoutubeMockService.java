package com.focusings.focusingsworld.data.youtube;

import android.content.Context;

import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.data.youtube.recentvideos.request.RecentVideosResponseDto;
import com.focusings.focusingsworld.data.youtube.remote.YoutubeService;
import com.google.gson.Gson;

import retrofit2.http.Query;
import rx.Observable;

public final class YoutubeMockService implements YoutubeService {

    private final Context context;

    public YoutubeMockService(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public Observable<RecentVideosResponseDto> getRecentVideosFromChannel(@Query("channelId") String channelId, @Query("key") String key) {
        String json = JsonLoader.loadJson(context, R.raw.getrecentvideosfromchannel);
        RecentVideosResponseDto recentVideosResponseDto = new Gson().fromJson(json, RecentVideosResponseDto.class);
        return Observable.just(recentVideosResponseDto);
    }
}
