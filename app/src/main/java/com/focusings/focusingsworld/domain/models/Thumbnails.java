package com.focusings.focusingsworld.domain.models;

/**
 * Created by usuario on 04/04/2017.
 */

public class Thumbnails {

    private Thumbnail defaultThumbnail;

    private Thumbnail mediumThumbnail;

    private Thumbnail highThumbnail;

    public Thumbnail getDefaultThumbnail() {
        return defaultThumbnail;
    }

    public void setDefaultThumbnail(Thumbnail defaultThumbnail) {
        this.defaultThumbnail = defaultThumbnail;
    }

    public Thumbnail getMediumThumbnail() {
        return mediumThumbnail;
    }

    public void setMediumThumbnail(Thumbnail mediumThumbnail) {
        this.mediumThumbnail = mediumThumbnail;
    }

    public Thumbnail getHighThumbnail() {
        return highThumbnail;
    }

    public void setHighThumbnail(Thumbnail highThumbnail) {
        this.highThumbnail = highThumbnail;
    }
}
