package com.focusings.focusingsworld.utils.prefs;

import com.github.pwittchen.prefser.library.Prefser;
import com.github.pwittchen.prefser.library.TypeToken;

import rx.Observable;

public abstract class PrefsCache<D> {

    private final String key;
    private final Prefser prefser;

    public PrefsCache(String key, Prefser prefser) {
        this.key = key;
        this.prefser = prefser;
    }

    public void put(D data) {
        this.prefser.put(key, data);
    }

    public Observable<D> get() {

        D data = this.prefser.get(key, getTypeToken(), null);

        if (data != null) {
            return Observable.just(data);
        } else {
            return Observable.empty();
        }
    }

    public boolean exists() {
        return this.prefser.contains(key);
    }

    abstract public TypeToken<D> getTypeToken();
}
