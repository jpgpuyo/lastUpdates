package com.focusings.focusingsworld.infrastructure.dagger.perapplication.modules;

import android.content.Context;

import com.focusings.focusingsworld.data.youtube.api.YoutubeApiConstants;
import com.jpuyo.android.infrastructure.connectivity.NetworkConnection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    NetworkConnection provideNetwork(Context context) {
        return new NetworkConnection(context);
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
}

