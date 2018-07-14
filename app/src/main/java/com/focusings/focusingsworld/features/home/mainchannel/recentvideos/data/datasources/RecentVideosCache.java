package com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data.datasources;

import com.focusings.focusingsworld.core.preferences.PrefsCache;
import com.focusings.focusingsworld.models.YoutubeVideo;
import com.github.pwittchen.prefser.library.Prefser;
import com.github.pwittchen.prefser.library.TypeToken;

import java.util.List;

public class RecentVideosCache extends PrefsCache<List<YoutubeVideo>> {

    public RecentVideosCache(String key, Prefser prefser) {
        super(key, prefser);
    }

    @Override
    public TypeToken<List<YoutubeVideo>> getTypeToken() {
        return new TypeToken<List<YoutubeVideo>>(){};
    }


}
