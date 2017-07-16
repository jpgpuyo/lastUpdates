package com.focusings.focusingsworld.presentation.main;

import android.support.annotation.StringRes;

public interface MainView {
    void showErrorMessage(@StringRes int resourceId);
    void hideErrorMessage();
}