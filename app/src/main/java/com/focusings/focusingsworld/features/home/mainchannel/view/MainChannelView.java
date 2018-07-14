package com.focusings.focusingsworld.features.home.mainchannel.view;

import com.focusings.focusingsworld.core.exception.DefaultErrorBundle;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;

import java.util.List;

public interface MainChannelView {
    void hideLoading();
    void renderYoutubeVideoList(List<YoutubeVideo> youtubeVideoList);
    void showNetworkError();
    void showError(DefaultErrorBundle exception);
}
