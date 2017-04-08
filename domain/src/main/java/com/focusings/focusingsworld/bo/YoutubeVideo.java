package com.focusings.focusingsworld.bo;

public class YoutubeVideo {

    private String title;

    private String image;

    private String url;

    private Thumbnails thumbnails;

    public YoutubeVideo() {
    }

    public YoutubeVideo(String image, String title, String url) {
        this.image = image;
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }
}
