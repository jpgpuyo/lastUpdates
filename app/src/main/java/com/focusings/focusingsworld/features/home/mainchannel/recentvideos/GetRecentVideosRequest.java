package com.focusings.focusingsworld.features.home.mainchannel.recentvideos;

public class GetRecentVideosRequest {

    private boolean refresh;

    public GetRecentVideosRequest() {
    }

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }
}
