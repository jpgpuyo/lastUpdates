package com.focusings.focusingsworld.bo;

public class YoutubeVideo {
    private String title;
    private String image;
    private String url;
 
    public YoutubeVideo(String image, String title, String url) {
        this.image = image;
        this.title = title;
        this.url=url;
    }
    public String getImage() {
        return image;
    }
    public String getTitle() {
        return title;
    }
    public String getUrl() {
        return url;
    }
}
