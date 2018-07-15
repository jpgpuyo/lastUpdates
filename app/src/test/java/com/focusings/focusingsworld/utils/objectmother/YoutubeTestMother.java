package com.focusings.focusingsworld.utils.objectmother;

import com.focusings.focusingsworld.data.youtube.recentvideos.CloudRecentVideosDataStore;
import com.focusings.focusingsworld.data.youtube.core.api.YoutubeApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import com.focusings.focusingsworld.utils.mockwebserver.TestApiClient;

public class YoutubeTestMother {

    public static CloudRecentVideosDataStore givenYoutubeRemoteDataStore(TestApiClient testApiClient) {
        return new CloudRecentVideosDataStore(YoutubeTestMother.givenYoutubeService(testApiClient));
    }

    public static YoutubeApi givenYoutubeService(TestApiClient testApiClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(testApiClient.getBaseEndpoint())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(YoutubeApi.class);
    }
}
