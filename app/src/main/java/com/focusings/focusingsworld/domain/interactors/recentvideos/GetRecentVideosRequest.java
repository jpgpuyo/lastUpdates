package com.focusings.focusingsworld.domain.interactors.recentvideos;

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
