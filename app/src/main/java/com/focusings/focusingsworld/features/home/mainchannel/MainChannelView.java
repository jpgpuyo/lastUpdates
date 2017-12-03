package com.focusings.focusingsworld.features.home.mainchannel;

import com.focusings.focusingsworld.models.YoutubeVideo;

import java.util.List;

public interface MainChannelView {
    void hideLoading();
    void renderYoutubeVideoList(List<YoutubeVideo> youtubeVideoList);
    void showNetworkError();
}
