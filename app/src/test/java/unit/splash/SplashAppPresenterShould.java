package unit.splash;

import com.focusings.focusingsworld.features.splash.SplashAppPresenter;
import com.focusings.focusingsworld.features.splash.SplashView;
import com.focusings.focusingsworld.models.YoutubeVideo;
import com.jpuyo.android.infrastructure.interactor.UseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SplashAppPresenterShould {

    SplashAppPresenter splashAppPresenter;

    @Mock
    UseCase initAppUseCase;

    @Mock
    SplashView splashView;

    @Before
    public void setUp() throws Exception {
        splashAppPresenter = new SplashAppPresenter(initAppUseCase);
        splashAppPresenter.setView(splashView);
    }

    @Test
    public void call_init_app_finished_when_init_app_use_case_has_finished() {
        doAnswer(invocation -> {
            ((Subscriber) invocation.getArguments()[0]).onNext(givenYoutubeVideoList());
            ((Subscriber) invocation.getArguments()[0]).onCompleted();
            return null;
        }).when(initAppUseCase).execute(any(Subscriber.class));

        splashAppPresenter.execute();

        verify(splashView, times(1)).onInitAppFinished();
    }

    private List<YoutubeVideo> givenYoutubeVideoList() {
        List<YoutubeVideo> youtubeVideoList = new ArrayList<>();
        return youtubeVideoList;
    }
}
