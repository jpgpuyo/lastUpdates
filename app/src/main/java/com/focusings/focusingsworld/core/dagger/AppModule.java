package com.focusings.focusingsworld.core.dagger;

import android.app.Application;
import android.content.Context;

import com.focusings.focusingsworld.core.connectivity.Network;
import com.focusings.focusingsworld.core.connectivity.NetworkConnection;
import com.focusings.focusingsworld.core.executor.JobExecutor;
import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.core.executor.UIThread;
import com.focusings.focusingsworld.data.youtube.api.YoutubeApi;
import com.focusings.focusingsworld.data.youtube.api.YoutubeApiConstants;
import com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data.MemoryDataStore;
import com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data.RecentVideosRepository;
import com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data.datasources.RecentVideosCloud;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor() {
        return new JobExecutor();
    }


    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread() {
        return new UIThread();
    }

    @Provides
    @Singleton
    Network provideNetwork(Context context) {
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

    @Provides
    @Singleton
    RecentVideosRepository provideRcentVideosRepository(RecentVideosCloud recentVideosCloud,
                                                        MemoryDataStore memoryDataStore) {
        return new RecentVideosRepository(recentVideosCloud, memoryDataStore);
    }

    @Provides
    @Singleton
    RecentVideosCloud provideYoutubeRemoteDataStore(Retrofit retrofit) {
        YoutubeApi youtubeApi = retrofit.create(YoutubeApi.class);
        return new RecentVideosCloud(youtubeApi);
    }

    @Provides
    @Singleton
    MemoryDataStore provideMemoryDataStore() {
        return new MemoryDataStore();
    }
}
