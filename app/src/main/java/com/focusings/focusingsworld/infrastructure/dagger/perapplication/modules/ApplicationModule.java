package com.focusings.focusingsworld.infrastructure.dagger.perapplication.modules;

import android.content.Context;

import com.focusings.focusingsworld.AndroidApplication;
import com.focusings.focusingsworld.presentation.init.InitAppModule;
import com.jpuyo.android.infrastructure.connectivity.Network;
import com.jpuyo.android.infrastructure.connectivity.NetworkConnection;
import com.jpuyo.android.infrastructure.executor.JobExecutor;
import com.jpuyo.android.infrastructure.executor.PostExecutionThread;
import com.jpuyo.android.infrastructure.executor.ThreadExecutor;
import com.jpuyo.android.infrastructure.executor.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module(includes = {InitAppModule.class, YoutubeModule.class})
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

    @Provides
    @Singleton
    Network provideNetwork() {
        return new NetworkConnection(application);
    }
}
