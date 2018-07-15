/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.focusings.focusingsworld.unit.usecase;


import com.focusings.focusingsworld.core.executor.PostExecutionThread;
import com.focusings.focusingsworld.core.executor.ThreadExecutor;
import com.focusings.focusingsworld.core.interactor.DefaultSubscriber;
import com.focusings.focusingsworld.core.interactor.UseCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.TestScheduler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UseCaseShould {

    private UseCaseTestClass useCase;

    protected UseCaseTestSubscriber<Object> testSubscriber;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        this.useCase = new UseCaseTestClass(mockThreadExecutor, mockPostExecutionThread);
        this.testSubscriber = new UseCaseTestSubscriber<>();
        given(mockPostExecutionThread.getScheduler()).willReturn(new TestScheduler());
    }

    @Test
    public void testBuildUseCaseObservableReturnCorrectResult() {
        useCase.execute(testSubscriber, Params.EMPTY);

        assertThat(testSubscriber.valuesCount).isZero();
    }

    @Test
    public void testSubscriptionWhenExecutingUseCase() {
        useCase.execute(testSubscriber, Params.EMPTY);
        useCase.unsubscribe();

        assertThat(testSubscriber.isUnsubscribed()).isTrue();
    }

    @Test
    public void testShouldFailWhenExecuteWithNullObserver() {
        expectedException.expect(IllegalArgumentException.class);
        useCase.execute(null, Params.EMPTY);
    }

    private static class UseCaseTestClass extends UseCase<Object, Params> {

        UseCaseTestClass(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
            super(threadExecutor, postExecutionThread);
        }

        @Override
        public Observable<Object> buildUseCaseObservable(Params params) {
            return Observable.empty();
        }

        @Override
        public void execute(Subscriber subscriber, Params params) {
            super.execute(subscriber, params);
        }
    }

    private static class UseCaseTestSubscriber<T> extends DefaultSubscriber<T> {
        private int valuesCount = 0;

        @Override
        public void onNext(T value) {
            valuesCount++;
        }

        @Override
        public void onError(Throwable e) {
            // no-op by default.
        }

        @Override
        public void onCompleted() {
            // no-op by default.
        }
    }

    private static class Params {
        private static Params EMPTY = new Params();

        private Params() {
        }
    }
}
