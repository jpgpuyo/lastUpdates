package unit.youtube.infrastructure;

import com.focusings.focusingsworld.data.youtube.recentvideos.CloudRecentVideosDataStore;
import com.focusings.focusingsworld.data.youtube.core.api.YoutubeApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class YoutubeTestMother {

    public static CloudRecentVideosDataStore givenYoutubeRemoteDataStore(ApiClientTest apiClientTest) {
        return new CloudRecentVideosDataStore(YoutubeTestMother.givenYoutubeService(apiClientTest));
    }

    public static YoutubeApi givenYoutubeService(ApiClientTest apiClientTest) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiClientTest.getBaseEndpoint())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(YoutubeApi.class);
    }
}
