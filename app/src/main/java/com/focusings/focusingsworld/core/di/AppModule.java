package com.focusings.focusingsworld.core.di;

import android.app.Application;
import android.content.Context;

import com.focusings.focusingsworld.core.utils.network.NetworkUtils;
import com.focusings.focusingsworld.core.utils.network.AndroidNetworkUtils;
import com.focusings.focusingsworld.core.executor.JobExecutor;
import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.core.executor.UIThread;
import com.focusings.focusingsworld.data.youtube.core.api.YoutubeApi;
import com.focusings.focusingsworld.data.youtube.core.api.YoutubeApiConstants;
import com.focusings.focusingsworld.data.youtube.core.memory.MemoryYoutubeDataStore;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosRepository;
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
    RecentVideosRepository provideRcentVideosRepository(CloudRecentVideosDataStore cloudRecentVideosDataStore,
                                                        MemoryYoutubeDataStore memoryYoutubeDataStore) {
        return new RecentVideosRepository(cloudRecentVideosDataStore, memoryYoutubeDataStore);
    }

    @Provides
    @Singleton
    CloudRecentVideosDataStore provideYoutubeRemoteDataStore(Retrofit retrofit) {
        YoutubeApi youtubeApi = retrofit.create(YoutubeApi.class);
        return new CloudRecentVideosDataStore(youtubeApi);
    }

    @Provides
    @Singleton
    MemoryYoutubeDataStore provideMemoryDataStore() {
        return new MemoryYoutubeDataStore();
    }
}
