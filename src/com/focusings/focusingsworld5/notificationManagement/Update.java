package com.focusings.focusingsworld5.notificationManagement;

public class Update {
    private String title;
    private String channel;
 
    public Update(String title, String channel) {
        this.title = title;
        this.channel=channel;
    }
    public String getTitle() {
        return title;
    }
    public String getChannel() {
        return channel;
    }
}
