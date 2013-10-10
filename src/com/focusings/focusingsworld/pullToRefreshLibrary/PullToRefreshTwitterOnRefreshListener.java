package com.focusings.focusingsworld.pullToRefreshLibrary;

import com.focusings.focusingsworld.TwitterParser.AsyncTwitterParser;



public class PullToRefreshTwitterOnRefreshListener implements PullToRefreshListView.OnRefreshListener{
	
    public PullToRefreshTwitterOnRefreshListener(){}

    @Override
    public void onRefresh() {
        // Your code to refresh the list contents
    	new AsyncTwitterParser().execute("true");
    	
        // Make sure you call listView.onRefreshComplete()
        // when the loading is done. This can be done from here or any
        // other place, like on a broadcast receive from your loading
        // service or the onPostExecute of your AsyncTask.
    }
  
}