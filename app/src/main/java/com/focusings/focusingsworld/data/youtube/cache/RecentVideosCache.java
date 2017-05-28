package com.focusings.focusingsworld.data.youtube.cache;

import com.focusings.focusingsworld.domain.models.YoutubeVideo;
import com.focusings.focusingsworld.infrastructure.preferences.PrefsCache;
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