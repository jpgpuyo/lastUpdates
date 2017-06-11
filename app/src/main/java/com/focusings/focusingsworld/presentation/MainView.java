package com.focusings.focusingsworld.presentation;

import android.support.annotation.StringRes;

public interface MainView {
    void showErrorMessage(@StringRes int resourceId);
    void hideErrorMessage();
}