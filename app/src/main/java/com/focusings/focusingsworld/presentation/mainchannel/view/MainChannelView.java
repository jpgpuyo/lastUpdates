package com.focusings.focusingsworld.presentation.mainchannel.view;

import com.focusings.focusingsworld.presentation.mainchannel.model.YoutubeVideoModel;

import java.util.List;

public interface MainChannelView {
    void hideLoading();
    void renderYoutubeVideoList(List<YoutubeVideoModel> youtubeVideoModelCollection);
    void showNetworkError();
}
