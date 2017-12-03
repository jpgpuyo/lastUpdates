package com.focusings.focusingsworld.data.youtube.api.dto;

import com.google.gson.annotations.SerializedName;

public class IdDto {

    @SerializedName("kind")
    private String kind;

    @SerializedName("videoId")
    private String videoId;

    @SerializedName("playlistId")
    private String playlistId;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
}
