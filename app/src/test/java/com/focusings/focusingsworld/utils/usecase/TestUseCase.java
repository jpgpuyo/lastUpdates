package com.focusings.focusingsworld.utils.usecase;

import com.focusings.focusingsworld.core.executor.JobExecutor;
import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.core.executor.UIThread;

import org.junit.Before;
import org.junit.Rule;

import rx.observers.TestSubscriber;
import com.focusings.focusingsworld.utils.testrules.ImmediateSchedulersTestRule;

public class TestUseCase {

    @Rule
    public ImmediateSchedulersTestRule immediateSchedulersTestRule = new ImmediateSchedulersTestRule();

    protected ThreadExecutor threadExecutor;

    protected PostExecutionThread postExecutionThread;

    protected TestSubscriber testSubscriber;

    @Before
    public void setUp() {
        threadExecutor = new JobExecutor();
        postExecutionThread = new UIThread();
        testSubscriber = TestSubscriber.create();
    }
}
