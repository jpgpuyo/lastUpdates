package com.focusings.focusingsworld.TwitterParser;

import java.util.List;

public interface AsyncTwitterResponse {
    void printTwitterElements(List<TweetInfo> output, boolean needToCallOnRefreshComplete);
}