package com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data;

import com.focusings.focusingsworld.models.YoutubeVideo;

import java.util.List;

public class MemoryDataStore {

    List<YoutubeVideo> recentVideosList;

    public void saveRecentVideosFromChannel(List<YoutubeVideo> recentVideosList) {
        this.recentVideosList = recentVideosList;
    }

    public List<YoutubeVideo> getRecentVideosFromChannel() {
        return recentVideosList;
    }
}
