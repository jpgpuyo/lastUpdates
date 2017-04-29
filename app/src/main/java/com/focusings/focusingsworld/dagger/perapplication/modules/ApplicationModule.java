package com.focusings.focusingsworld.dagger.perapplication.modules;

import android.content.Context;

import com.focusings.focusingsworld.AndroidApplication;
import com.focusings.focusingsworld.UIThread;
import com.focusings.focusingsworld.executor.JobExecutor;
import com.focusings.focusingsworld.executor.PostExecutionThread;
import com.focusings.focusingsworld.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module(includes = {YoutubeModule.class})
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor() {
        return new JobExecutor();
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread() {
        return new UIThread();
    }
}
