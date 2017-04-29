package unit.youtube.infrastructure;

import com.focusings.focusingsworld.repository.youtube.remote.YoutubeRemoteDataStore;
import com.focusings.focusingsworld.repository.youtube.remote.YoutubeService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by usuario on 15/04/2017.
 */

public class YoutubeTestMother {

    public static YoutubeRemoteDataStore givenYoutubeRemoteDataStore(ApiClientTest apiClientTest) {
        return new YoutubeRemoteDataStore(YoutubeTestMother.givenYoutubeService(apiClientTest));
    }

    public static YoutubeService givenYoutubeService(ApiClientTest apiClientTest) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiClientTest.getBaseEndpoint())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(YoutubeService.class);
    }
}
