package unit.splash;

import android.support.annotation.NonNull;

import com.focusings.focusingsworld.features.splash.SplashAppPresenter;
import com.focusings.focusingsworld.features.splash.SplashView;
import com.focusings.focusingsworld.models.YoutubeVideo;
import com.jpuyo.android.infrastructure.interactor.DefaultSubscriber;
import com.jpuyo.android.infrastructure.interactor.UseCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SplashAppPresenterShould {

    @Mock
    UseCase initAppUseCase;

    @Mock
    SplashView splashView;

    @Captor
    private ArgumentCaptor<DefaultSubscriber<List<YoutubeVideo>>> initAppSubscriberCaptor;

    @Test
    public void call_init_app_finished_when_init_app_use_case_has_finished() {
        SplashAppPresenter splashAppPresenter = givenSplashAppPresenter();

        splashAppPresenter.execute();

        verify(initAppUseCase).execute(initAppSubscriberCaptor.capture());
        initAppSubscriberCaptor.getValue().onNext(givenYoutubeVideoList());
        initAppSubscriberCaptor.getValue().onCompleted();
        verify(splashView, times(1)).onInitAppFinished();
    }

    private SplashAppPresenter givenSplashAppPresenter() {
        SplashAppPresenter splashAppPresenter = new SplashAppPresenter(initAppUseCase);
        splashAppPresenter.setView(splashView);
        return splashAppPresenter;
    }

    private List<YoutubeVideo> givenYoutubeVideoList() {
        List<YoutubeVideo> youtubeVideoList = new ArrayList<>();
        return youtubeVideoList;
    }
}
