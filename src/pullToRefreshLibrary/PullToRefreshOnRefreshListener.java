package pullToRefreshLibrary;

import com.focusings.focusingsworld5.MainActivity;
import com.focusings.focusingsworld5.YoutubeParser.AsyncYoutubeParser;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

public class PullToRefreshOnRefreshListener implements PullToRefreshListView.OnRefreshListener{

	private int tabNumber;
	
    public PullToRefreshOnRefreshListener(int tabNumber){
        this.tabNumber=tabNumber;
    }

    @Override
    public void onRefresh() {
        // Your code to refresh the list contents
    	new AsyncYoutubeParser().execute(MainActivity.properties.getProperty("Youtube_URL_part_1")+MainActivity.properties.getProperty("tab_"+tabNumber+"_channel_name")+MainActivity.properties.getProperty("Youtube_URL_part_2"),"true");
    	
        // Make sure you call listView.onRefreshComplete()
        // when the loading is done. This can be done from here or any
        // other place, like on a broadcast receive from your loading
        // service or the onPostExecute of your AsyncTask.
    }
  
}