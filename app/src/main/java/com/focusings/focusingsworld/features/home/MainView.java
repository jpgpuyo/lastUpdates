package com.focusings.focusingsworld.features.home;

import android.support.annotation.StringRes;

public interface MainView {
    void showErrorMessage(@StringRes int resourceId);
    void hideErrorMessage();
}