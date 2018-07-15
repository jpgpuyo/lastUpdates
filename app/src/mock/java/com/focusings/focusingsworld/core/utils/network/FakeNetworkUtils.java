package com.focusings.focusingsworld.core.utils.network;

import com.focusings.focusingsworld.core.utils.random.RandomUtils;

public class FakeNetworkUtils implements NetworkUtils{

    public FakeNetworkUtils() {
    }

    @Override
    public boolean isNetworkAvailable() {
        return RandomUtils.percent(50);
    }
}
