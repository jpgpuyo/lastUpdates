package com.focusings.focusingsworld.TwitterParser;

public class TweetInfo {
    private String userImage;
    private String userName;
    private String userTwitterAccount;
    private String tweetContent;
 
    public TweetInfo() {
    }

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserTwitterAccount(String userTwitterAccount) {
		this.userTwitterAccount = userTwitterAccount;
	}

	public void setTweetContent(String tweetContent) {
		this.tweetContent = tweetContent;
	}

	public String getUserImage() {
		return userImage;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserTwitterAccount() {
		return userTwitterAccount;
	}

	public String getTweetContent() {
		return tweetContent;
	}
    
}
