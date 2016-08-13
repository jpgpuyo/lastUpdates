package com.focusings.focusingsworld.YoutubeParser;

import android.os.AsyncTask;
import android.widget.TextView;

import com.focusings.focusingsworld.ImageAndTextList.ImageAndText;
import com.focusings.focusingsworld.ImageAndTextList.ImageAndTextListAdapter;
import com.focusings.focusingsworld.MainActivity;
import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.VideoInfo;
import com.focusings.focusingsworld.pullToRefreshLibrary.PullToRefreshListView;
import com.focusings.focusingsworld.pullToRefreshLibrary.PullToRefreshYoutubeOnRefreshListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockAsyncYoutubeParser extends AsyncTask<String, Integer,List<ImageAndText>> {

    private int tabNumber=1;
    private boolean needToCallOnRefreshComplete=false;
    private MainActivity mainActivity;

    public MockAsyncYoutubeParser(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<ImageAndText> doInBackground(String... params) {
        //List<ImageAndText> list=AsyncYoutubeParserGeneral.doInBackgroundGeneral(params);
        List<ImageAndText> l1 = new ArrayList<ImageAndText>();

        try {
            if (params[0].contains(MainActivity.properties.getProperty("tab_1_channel_name"))) {
                tabNumber = 1;
            }
            if (params[0].contains(MainActivity.properties.getProperty("tab_2_channel_name"))) {
                tabNumber = 2;
            }
            if (params[1].equals("true")) {
                needToCallOnRefreshComplete = true;
            }

            String urlLastVideo = "https://www.youtube.com/watch?v=a46Me73ykcQ";
            Date publishingDateLastVideo = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2016-08-12T15:00:01.000Z");
            VideoInfo vi = new VideoInfo(urlLastVideo, publishingDateLastVideo);

            //I create the MainActivity.lastUpdatePerChannel in the proper order
            MainActivity.lastUpdatePerChannel[tabNumber - 1] = vi;

            l1.add(new ImageAndText("https://i.ytimg.com/vi/a46Me73ykcQ/default.jpg", "¿ALGUNA VEZ ME HA GUSTADO UNA CHICA? #focurrespuestas", "https://www.youtube.com/watch?v=a46Me73ykcQ"));
            l1.add(new ImageAndText("https://i.ytimg.com/vi/W1-w17bTv7s/default.jpg", "LAS SOBRAS DE NAVIDAD PARA MÍ!!! #focumail", "https://www.youtube.com/watch?v=W1-w17bTv7s"));
            l1.add(new ImageAndText("https://i.ytimg.com/vi/779eElf2fS0/default.jpg", "RESPUESTAS PRÁCTICAS A PREGUNTAS ABSURDAS #ad", "https://www.youtube.com/watch?v=779eElf2fS0"));
            l1.add(new ImageAndText("https://i.ytimg.com/vi/fvPN69XrVi8/default.jpg", "SECTAS COMERCIALES. Cómo me captaron y cómo conseguí escapar", "https://www.youtube.com/watch?v=fvPN69XrVi8"));
            l1.add(new ImageAndText("https://i.ytimg.com/vi/jzpTXfKTs2M/default.jpg", "FRASES DE MADRE EN LA PLAYA", "https://www.youtube.com/watch?v=jzpTXfKTs2M"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l1;
    }

    @Override
    protected void onPostExecute(List<ImageAndText> result) {
        //this you will received result fired from async class of onPostExecute(result) method.
        ImageAndTextListAdapter adapter = new ImageAndTextListAdapter(mainActivity,result);

        TextView errorLog=null;
        PullToRefreshListView listView=null;
        if (tabNumber==1){
            errorLog=(TextView)mainActivity.findViewById(R.id.errorLog1);
            listView = (PullToRefreshListView)mainActivity.findViewById(R.id.pull_to_refresh_listview1);
        }
        if (tabNumber==2){
            errorLog=(TextView)mainActivity.findViewById(R.id.errorLog2);
            listView = (PullToRefreshListView)mainActivity.findViewById(R.id.pull_to_refresh_listview2);
        }
        if (tabNumber==3){
            errorLog=(TextView)mainActivity.findViewById(R.id.errorLogTwitter);
            listView = (PullToRefreshListView)mainActivity.findViewById(R.id.pull_to_refresh_listview_twitter);
        }

        if (result.size()==0){
            if (mainActivity.isOnline()==false){
                errorLog.setText(R.string.noInternetConnection);
            }else{
                errorLog.setText(R.string.noYoutubeConnection);
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
            listView.setOnRefreshListener(new PullToRefreshYoutubeOnRefreshListener(tabNumber,mainActivity));
            listView.setAdapter(adapter);
        }/*else{
			GridView gridView=null;
			if (tabNumber==1){
				gridView = (GridView)findViewById(R.id.gridview1);
				gridView.setAdapter(adapter);
			}
			if (tabNumber==2){
				gridView = (GridView)findViewById(R.id.gridview2);
				gridView.setAdapter(adapter);
			}
		}*/
    }
}