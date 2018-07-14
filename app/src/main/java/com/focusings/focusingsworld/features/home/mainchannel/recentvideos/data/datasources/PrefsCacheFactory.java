package com.focusings.focusingsworld.features.home.mainchannel.recentvideos.data.datasources;

import com.focusings.focusingsworld.core.preferences.PrefsCache;
import com.github.pwittchen.prefser.library.Prefser;

public class PrefsCacheFactory {

    private Prefser prefser;

    public static final String RECENT_VIDEOS = "recentvideos";

    public PrefsCacheFactory(Prefser prefser) {
        this.prefser = prefser;
    }

    public PrefsCache get(String key) {

        PrefsCache prefsCache = null;

        switch(key) {
            case RECENT_VIDEOS:
                prefsCache = new RecentVideosCache(key, prefser);
                break;
        }

        return prefsCache;
    }
}
