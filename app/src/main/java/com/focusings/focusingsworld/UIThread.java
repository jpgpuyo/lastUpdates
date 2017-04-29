package com.focusings.focusingsworld;

import android.os.Handler;
import android.os.Looper;

import com.focusings.focusingsworld.executor.PostExecutionThread;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

public class UIThread implements PostExecutionThread {

    private final Handler handler;

    public UIThread() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
