package com.focusings.focusingsworld.repository.youtube.remote.dto;

import com.google.gson.annotations.SerializedName;

public class YoutubeVideoDto {

    @SerializedName("kind")
    private String kind;

    @SerializedName("etag")
    private String etag;

    @SerializedName("id")
    private IdDto id;

    @SerializedName("snippet")
    private SnippetDto snippet;

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

    public IdDto getId() {
        return id;
    }

    public void setId(IdDto id) {
        this.id = id;
    }

    public SnippetDto getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetDto snippet) {
        this.snippet = snippet;
    }
}
