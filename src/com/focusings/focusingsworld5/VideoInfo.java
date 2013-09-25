package com.focusings.focusingsworld5;

import java.util.Date;

public class VideoInfo {
    private String url;
    private Date publishingDate;
 
    public VideoInfo(String url,Date publishingDate) {
        this.url=url;
        this.publishingDate=publishingDate;
    }
    public String getUrl() {
        return url;
    }
    public Date getPublishingDate() {
        return publishingDate;
    }
    public void setUrl(String url) {
        this.url=url;
    }
    public void setPublishingDate(Date publishingDate) {
    	this.publishingDate=publishingDate;
    }
}
