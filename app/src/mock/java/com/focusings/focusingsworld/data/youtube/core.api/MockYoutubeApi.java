package com.focusings.focusingsworld.data.youtube.core.api;

import android.content.Context;

import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.core.exception.NetworkConnectionException;
import com.focusings.focusingsworld.core.utils.network.FakeNetworkUtils;
import com.focusings.focusingsworld.core.utils.network.NetworkUtils;
import com.focusings.focusingsworld.data.youtube.core.api.dto.recentvideos.RecentVideosResponseDto;
import com.google.gson.Gson;

import retrofit2.http.Query;
import rx.Observable;

public final class MockYoutubeApi implements YoutubeApi {

    private final Context context;

    private final NetworkUtils networkUtils;

    public MockYoutubeApi(Context context, NetworkUtils fakeNetworkUtils) {
        this.context = context.getApplicationContext();
        this.networkUtils = fakeNetworkUtils;
    }

    @Override
    public Observable<RecentVideosResponseDto> getRecentVideosFromChannel(@Query("channelId") String channelId, @Query("key") String key) {
        if (networkUtils.isNetworkAvailable()) {
            String json = JsonLoader.loadJson(context, R.raw.getrecentvideosfromchannel);
            RecentVideosResponseDto recentVideosResponseDto = new Gson().fromJson(json, RecentVideosResponseDto.class);
            return Observable.just(recentVideosResponseDto);
        } else {
            return Observable.error(new NetworkConnectionException());
        }
    }
}
