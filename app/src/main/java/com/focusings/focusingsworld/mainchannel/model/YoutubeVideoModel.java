package com.focusings.focusingsworld.mainchannel.model;

import com.focusings.focusingsworld.base.renderer.ViewModel;

public class YoutubeVideoModel implements ViewModel {

    private long id;
    private String title;
    private String image;
    private String url;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        YoutubeVideoModel that = (YoutubeVideoModel) o;

        if (!title.equals(that.title)) return false;
        if (!image.equals(that.image)) return false;
        return url.equals(that.url);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + image.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
