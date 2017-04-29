package com.focusings.focusingsworld.repository.youtube.remote.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by usuario on 04/04/2017.
 */

public class SnippetDto {

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("channelId")
    private String channelId;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("thumbnails")
    private ThumbnailsDto thumbnails;

    @SerializedName("channelTitle")
    private String channelTitle;

    @SerializedName("liveBroadcastContent")
    private String liveBroadcastContent;

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ThumbnailsDto getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(ThumbnailsDto thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getLiveBroadcastContent() {
        return liveBroadcastContent;
    }

    public void setLiveBroadcastContent(String liveBroadcastContent) {
        this.liveBroadcastContent = liveBroadcastContent;
    }
}
