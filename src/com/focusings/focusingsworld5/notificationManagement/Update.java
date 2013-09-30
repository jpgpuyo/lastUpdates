package com.focusings.focusingsworld5.notificationManagement;

public class Update {
    private String title;
    private String channel;
    private int tabNumber;
 
    public Update(String title, String channel, int tabNumber) {
        this.title = title;
        this.channel=channel;
        this.tabNumber=tabNumber;
    }
    public String getTitle() {
        return title;
    }
    public String getChannel() {
        return channel;
    }
    public int getTabNumber() {
        return tabNumber;
    }
}
