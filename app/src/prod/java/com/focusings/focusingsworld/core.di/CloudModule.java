package com.focusings.focusingsworld.core.di;

import android.content.Context;

import com.focusings.focusingsworld.core.utils.network.AndroidNetworkUtils;
import com.focusings.focusingsworld.core.utils.network.NetworkUtils;
import com.focusings.focusingsworld.data.youtube.core.api.YoutubeApi;
import com.focusings.focusingsworld.data.youtube.core.api.YoutubeApiConstants;
import com.focusings.focusingsworld.data.youtube.recentvideos.CloudRecentVideosDataStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class CloudModule {

    @Provides
    @Singleton
    NetworkUtils provideNetwork(Context context) {
        return new AndroidNetworkUtils(context);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        return new Retrofit.Builder()
                .baseUrl(YoutubeApiConstants.ENDPOINT)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    CloudRecentVideosDataStore provideCloudRecentVideosDataStore(Retrofit retrofit) {
        YoutubeApi youtubeApi = retrofit.create(YoutubeApi.class);
        return new CloudRecentVideosDataStore(youtubeApi);
    }
}
