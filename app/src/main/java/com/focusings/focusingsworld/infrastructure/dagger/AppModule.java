package com.focusings.focusingsworld.infrastructure.dagger;

import android.app.Application;
import android.content.Context;

import com.focusings.focusingsworld.data.youtube.api.YoutubeApi;
import com.focusings.focusingsworld.data.youtube.api.YoutubeApiConstants;
import com.focusings.focusingsworld.data.youtube.cache.PrefsCacheFactory;
import com.focusings.focusingsworld.data.youtube.repository.recentvideos.RecentVideosRepository;
import com.focusings.focusingsworld.data.youtube.repository.recentvideos.datasources.RecentVideosCloud;
import com.focusings.focusingsworld.domain.repository.YoutubeRepository;
import com.focusings.focusingsworld.presentation.MainActivityComponent;
import com.focusings.focusingsworld.presentation.init.SplashActivityComponent;
import com.github.pwittchen.prefser.library.Prefser;
import com.jpuyo.android.infrastructure.connectivity.Network;
import com.jpuyo.android.infrastructure.connectivity.NetworkConnection;
import com.jpuyo.android.infrastructure.executor.JobExecutor;
import com.jpuyo.android.infrastructure.executor.PostExecutionThread;
import com.jpuyo.android.infrastructure.executor.ThreadExecutor;
import com.jpuyo.android.infrastructure.executor.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = {
        MainActivityComponent.class,
        SplashActivityComponent.class})
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
    YoutubeRepository provideYoutubeRepository(RecentVideosCloud recentVideosCloud,
                                               PrefsCacheFactory prefsCacheFactory) {
        return new RecentVideosRepository(recentVideosCloud, prefsCacheFactory);
    }

    @Provides
    @Singleton
    RecentVideosCloud provideYoutubeRemoteDataStore(Retrofit retrofit) {
        YoutubeApi youtubeApi = retrofit.create(YoutubeApi.class);
        return new RecentVideosCloud(youtubeApi);
    }

    @Provides
    @Singleton
    PrefsCacheFactory providePrefsCacheFactory(Context context) {
        Prefser prefser = new Prefser(context.getSharedPreferences("youtubePrefs", Context.MODE_PRIVATE));
        return new PrefsCacheFactory(prefser);
    }
}
