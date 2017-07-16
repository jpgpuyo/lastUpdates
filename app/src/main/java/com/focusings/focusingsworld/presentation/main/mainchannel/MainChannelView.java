package com.focusings.focusingsworld.presentation.main.mainchannel;

import com.focusings.focusingsworld.domain.models.YoutubeVideo;

import java.util.List;

public interface MainChannelView {
    void hideLoading();
    void renderYoutubeVideoList(List<YoutubeVideo> youtubeVideoList);
    void showNetworkError();
}
