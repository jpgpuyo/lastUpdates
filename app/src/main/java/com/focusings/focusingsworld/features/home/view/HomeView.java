package com.focusings.focusingsworld.features.home.view;

import android.support.annotation.StringRes;

public interface HomeView {
    void showErrorMessage(@StringRes int resourceId);
    void hideErrorMessage();
}