package com.focusings.focusingsworld.data.youtube.core.api.dto.recentvideos;

import com.focusings.focusingsworld.data.youtube.core.api.dto.PageInfoDto;
import com.focusings.focusingsworld.data.youtube.core.api.dto.YoutubeVideoDto;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecentVideosResponseDto {

    @SerializedName("kind")
    private String kind;

    @SerializedName("etag")
    private String etag;

    @SerializedName("nextPageToken")
    private String nextPageToken;

    @SerializedName("regionCode")
    private String regionCode;

    @SerializedName("pageInfo")
    private PageInfoDto pageInfo;

    @SerializedName("items")
    private List<YoutubeVideoDto> items;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public PageInfoDto getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoDto pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<YoutubeVideoDto> getItems() {
        return items;
    }

    public void setItems(List<YoutubeVideoDto> items) {
        this.items = items;
    }
}
