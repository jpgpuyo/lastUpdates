package com.focusings.focusingsworld.presentation.mainchannel.view;

import com.focusings.focusingsworld.domain.models.YoutubeVideo;

import java.util.List;

public interface MainChannelView {
    void hideLoading();
    void renderYoutubeVideoList(List<YoutubeVideo> youtubeVideoList);
    void showNetworkError();
}
