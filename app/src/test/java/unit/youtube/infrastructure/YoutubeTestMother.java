package unit.youtube.infrastructure;

import com.focusings.focusingsworld.data.youtube.repository.recentvideos.datasources.RecentVideosCloud;
import com.focusings.focusingsworld.data.youtube.api.YoutubeApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by usuario on 15/04/2017.
 */

public class YoutubeTestMother {

    public static RecentVideosCloud givenYoutubeRemoteDataStore(ApiClientTest apiClientTest) {
        return new RecentVideosCloud(YoutubeTestMother.givenYoutubeService(apiClientTest));
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
