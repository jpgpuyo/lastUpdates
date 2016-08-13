package com.focusings.focusingsworld.TwitterParser;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.focusings.focusingsworld.MainActivity;
import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.pullToRefreshLibrary.PullToRefreshListView;
import com.focusings.focusingsworld.pullToRefreshLibrary.PullToRefreshTwitterOnRefreshListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MockAsyncTwitterParser extends AsyncTask<String, Integer,List<TweetInfo>> {

	private MainActivity mainActivity;
	private boolean needToCallOnRefreshComplete=false;

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	public MockAsyncTwitterParser(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
	
	@Override
	protected List<TweetInfo> doInBackground(String... params) {
		List<TweetInfo> tweets=new ArrayList<TweetInfo>();
	
		if (params[0].equals("true")){
			needToCallOnRefreshComplete=true;
		}
		
		//Get tweets info
		try {
			String timeLineUser= loadMockJsonUserTimeLine();
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

	private String loadMockJsonUserTimeLine() {
		String json = null;
		try {
			InputStream is = mainActivity.getAssets().open("mock_twitter_timeline.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;
	}
	
	@Override
	protected void onPostExecute(List<TweetInfo> tweets) { 
		//this you will received result fired from async class of onPostExecute(result) method.
		TweetsListAdapter adapter = new TweetsListAdapter(mainActivity,tweets);
		
		TextView loadingTwitterView=(TextView)mainActivity.findViewById(R.id.loadingTwitter);
		loadingTwitterView.setText("");
		
		TextView errorLog=null;
		PullToRefreshListView listView=null;
		errorLog=(TextView)mainActivity.findViewById(R.id.errorLogTwitter);
		listView = (PullToRefreshListView)mainActivity.findViewById(R.id.pull_to_refresh_listview_twitter);
		
		if (tweets.size()==0){
			if (mainActivity.isOnline()==false){				
				errorLog.setText(R.string.noInternetConnection);
			}else{
				errorLog.setText(R.string.noTwitterConnection);
			}
		}else{
			errorLog.setText("");
		}
		
		//According to the pullToRefresh library I'm using, I need to call listView.onRefreshComplete() 
		//so that the spinner stops, but I only need to do this if I come from onRefreshListener (because it
		//means the spinner was already been displayed), if I come from initializations the spinner isn't displayed,
		//so, I shouldn't call listView.onRefreshComplete()
		if (needToCallOnRefreshComplete && listView!=null){
			listView.onRefreshComplete();
		}
		
		//Case of listView
		if (listView!=null){
			listView.setOnRefreshListener(new PullToRefreshTwitterOnRefreshListener(mainActivity));		
			listView.setAdapter(adapter);
		}	
	}
}