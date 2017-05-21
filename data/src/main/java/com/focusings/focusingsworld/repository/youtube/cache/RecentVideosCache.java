package com.focusings.focusingsworld.repository.youtube.cache;

import com.focusings.focusingsworld.bo.YoutubeVideo;
import com.focusings.focusingsworld.utils.prefs.PrefsCache;
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
