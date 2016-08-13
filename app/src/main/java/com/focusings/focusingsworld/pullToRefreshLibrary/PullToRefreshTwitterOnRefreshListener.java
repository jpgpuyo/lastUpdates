package com.focusings.focusingsworld.pullToRefreshLibrary;

import com.focusings.focusingsworld.MainActivity;
import com.focusings.focusingsworld.TwitterParser.AsyncTwitterParser;



public class PullToRefreshTwitterOnRefreshListener implements PullToRefreshListView.OnRefreshListener{
	
	private MainActivity mainActivity;
	
    public PullToRefreshTwitterOnRefreshListener(MainActivity mainActivity){
    	this.mainActivity=mainActivity;
    }

    @Override
    public void onRefresh() {
        // Your code to refresh the list contents
    	new AsyncTwitterParser(mainActivity).execute("true");
    	
        // Make sure you call listView.onRefreshComplete()
        // when the loading is done. This can be done from here or any
        // other place, like on a broadcast receive from your loading
        // service or the onPostExecute of your AsyncTask.
    }
  
}