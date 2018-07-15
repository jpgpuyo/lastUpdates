package com.focusings.focusingsworld.core.utils.rx;

import rx.Observable;

public class RxUtils {

    public static Observable asObservable(Object object) {
        return Observable.defer(() -> Observable.just(object));
    }
}
