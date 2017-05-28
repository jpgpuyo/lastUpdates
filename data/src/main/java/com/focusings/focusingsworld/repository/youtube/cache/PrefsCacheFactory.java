package com.focusings.focusingsworld.repository.youtube.cache;

import com.focusings.focusingsworld.utils.prefs.PrefsCache;
import com.github.pwittchen.prefser.library.Prefser;

/**
 * Created by usuario on 21/05/2017.
 */

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
