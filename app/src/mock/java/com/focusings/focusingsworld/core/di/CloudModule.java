package com.focusings.focusingsworld.core.di;

import android.content.Context;

import com.focusings.focusingsworld.core.utils.network.FakeNetworkUtils;
import com.focusings.focusingsworld.core.utils.network.NetworkUtils;
import com.focusings.focusingsworld.data.youtube.core.api.MockYoutubeApi;
import com.focusings.focusingsworld.data.youtube.core.api.YoutubeApi;
import com.focusings.focusingsworld.data.youtube.recentvideos.CloudRecentVideosDataStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CloudModule {

    @Provides
    @Singleton
    NetworkUtils provideNetwork() {
        return new FakeNetworkUtils();
    }

    @Provides
    @Singleton
    CloudRecentVideosDataStore provideCloudRecentVideosDataStore(Context context, NetworkUtils networkUtils) {
        YoutubeApi youtubeApi = new MockYoutubeApi(context, networkUtils);
        return new CloudRecentVideosDataStore(youtubeApi);
    }
}
