package com.focusings.focusingsworld.data.youtube.api.dto;

import com.google.gson.annotations.SerializedName;

public class ThumbnailsDto {

    @SerializedName("default")
    private ThumbnailDto defaultThumbnail;

    @SerializedName("medium")
    private ThumbnailDto mediumThumbnail;

    @SerializedName("high")
    private ThumbnailDto highThumbnail;

    public ThumbnailDto getDefaultThumbnail() {
        return defaultThumbnail;
    }

    public void setDefaultThumbnail(ThumbnailDto defaultThumbnail) {
        this.defaultThumbnail = defaultThumbnail;
    }

    public ThumbnailDto getMediumThumbnail() {
        return mediumThumbnail;
    }

    public void setMediumThumbnail(ThumbnailDto mediumThumbnail) {
        this.mediumThumbnail = mediumThumbnail;
    }

    public ThumbnailDto getHighThumbnail() {
        return highThumbnail;
    }

    public void setHighThumbnail(ThumbnailDto highThumbnail) {
        this.highThumbnail = highThumbnail;
    }
}
