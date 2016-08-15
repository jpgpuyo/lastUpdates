package com.focusings.focusingsworld.bo;

public class ImageAndText {
    private String imageUrl;
    private String text;
    private String url;
 
    public ImageAndText(String imageUrl, String text, String url) {
        this.imageUrl = imageUrl;
        this.text = text;
        this.url=url;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getText() {
        return text;
    }
    public String getUrl() {
        return url;
    }
}
