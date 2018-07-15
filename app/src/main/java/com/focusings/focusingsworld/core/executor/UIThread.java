package com.focusings.focusingsworld.core.executor;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

public class UIThread implements PostExecutionThread {

    public UIThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
