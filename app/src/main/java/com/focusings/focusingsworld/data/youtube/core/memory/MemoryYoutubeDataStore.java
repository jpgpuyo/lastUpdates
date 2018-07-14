package com.focusings.focusingsworld.data.youtube.core.memory;

import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;

import java.util.List;

public class MemoryYoutubeDataStore {

    List<YoutubeVideo> recentVideosList;

    public void saveRecentVideosFromChannel(List<YoutubeVideo> recentVideosList) {
        this.recentVideosList = recentVideosList;
    }

    public List<YoutubeVideo> getRecentVideosFromChannel() {
        return recentVideosList;
    }
}
