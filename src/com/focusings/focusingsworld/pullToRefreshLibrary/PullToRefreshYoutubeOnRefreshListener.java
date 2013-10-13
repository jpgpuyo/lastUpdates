package com.focusings.focusingsworld.pullToRefreshLibrary;

import com.focusings.focusingsworld.MainActivity;
import com.focusings.focusingsworld.YoutubeParser.AsyncYoutubeParser;


public class PullToRefreshYoutubeOnRefreshListener implements PullToRefreshListView.OnRefreshListener{

	private int tabNumber;
	private MainActivity mainActivity;
	
    public PullToRefreshYoutubeOnRefreshListener(int tabNumber,MainActivity mainActivity){
        this.tabNumber=tabNumber;
        this.mainActivity=mainActivity;
    }

    @Override
    public void onRefresh() {
        // Your code to refresh the list contents
    	new AsyncYoutubeParser(mainActivity).execute(MainActivity.properties.getProperty("Youtube_URL_part_1")+MainActivity.properties.getProperty("tab_"+tabNumber+"_channel_name")+MainActivity.properties.getProperty("Youtube_URL_part_2"),"true");
    	
        // Make sure you call listView.onRefreshComplete()
        // when the loading is done. This can be done from here or any
        // other place, like on a broadcast receive from your loading
        // service or the onPostExecute of your AsyncTask.
    }
  
}