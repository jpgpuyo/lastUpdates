package com.focusings.focusingsworld.repository.youtube.remote.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by usuario on 04/04/2017.
 */

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
