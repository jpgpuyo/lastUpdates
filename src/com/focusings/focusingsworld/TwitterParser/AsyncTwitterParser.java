package com.focusings.focusingsworld.TwitterParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncTwitterParser extends AsyncTask<String, Integer,List<TweetInfo>> {

	public static AsyncTwitterResponse delegate=null;
	private boolean needToCallOnRefreshComplete=false;
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	
	@Override
	protected List<TweetInfo> doInBackground(String... params) {
		List<TweetInfo> tweets=new ArrayList<TweetInfo>();
	
		if (params[0].equals("true")){
			needToCallOnRefreshComplete=true;
		}
		
		//Get tweets info
		try {
			String timeLineUser=TwitterUtils.getTimelineForUser();
			JSONArray jsonArray = new JSONArray(timeLineUser);
			JSONObject jsonObject;

			for (int i = 0; i < jsonArray.length(); i++) {
				
				jsonObject = (JSONObject) jsonArray.get(i);
				TweetInfo currentTweet = new TweetInfo();

				//Case retweet I need to access to the data in retweeted_status
				if (jsonObject.has("retweeted_status")){
					jsonObject=jsonObject.getJSONObject("retweeted_status");
				}
				
				currentTweet.setTweetContent(jsonObject.getString("text"));
				currentTweet.setUserImage(jsonObject.getJSONObject("user").getString("profile_image_url"));
				currentTweet.setUserName(jsonObject.getJSONObject("user").getString("name"));
				currentTweet.setUserTwitterAccount("@"+jsonObject.getJSONObject("user").getString("screen_name"));
							
				tweets.add(i, currentTweet);
			}

		} catch (Exception e) {
			Log.e("JSON error: ", Log.getStackTraceString(e));        
		}
		return tweets;
	}
	
	@Override
	protected void onPostExecute(List<TweetInfo> tweets) { 
		delegate.printTwitterElements(tweets,needToCallOnRefreshComplete);	
	}
}