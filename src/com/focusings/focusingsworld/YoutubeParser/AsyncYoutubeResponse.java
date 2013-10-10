package com.focusings.focusingsworld.YoutubeParser;

import java.util.List;

import com.focusings.focusingsworld.ImageAndTextList.ImageAndText;

public interface AsyncYoutubeResponse {
    void printYoutubeElements(List<ImageAndText> output,int tabNumber,boolean needToCallOnRefreshComplete);
}