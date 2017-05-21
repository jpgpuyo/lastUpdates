package com.focusings.focusingsworld.repository.youtube.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.focusings.focusingsworld.bo.YoutubeVideo;
import com.github.pwittchen.prefser.library.Prefser;
import com.github.pwittchen.prefser.library.TypeToken;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class YoutubePrefsDataStore implements YoutubeLocalDataStore {

    private final Prefser prefser;

    private final static String RECENT_VIDEOS_LIST = "recentvideos.list";
    private final static String RECENT_VIDEOS = "recentvideos";

    public YoutubePrefsDataStore(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(RECENT_VIDEOS, Context.MODE_PRIVATE);
        this.prefser = new Prefser(sharedPreferences);
    }

    @Override
    public void put(List<YoutubeVideo> youtubeVideoList) {
        prefser.put(RECENT_VIDEOS_LIST, youtubeVideoList);
    }

    @Override
    public Observable<List<YoutubeVideo>> get() {
        return Observable.just(
                prefser.get(RECENT_VIDEOS_LIST,
                new TypeToken<List<YoutubeVideo>>(){},
                new ArrayList<YoutubeVideo>())
        );
    }

    @Override
    public boolean isEmpty() {
        return !prefser.contains(RECENT_VIDEOS_LIST);
    }
}
