package com.focusings.focusingsworld.YoutubeParser;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.os.AsyncTask;
import android.widget.TextView;

import com.focusings.focusingsworld.MainActivity;
import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.VideoInfo;
import com.focusings.focusingsworld.ImageAndTextList.ImageAndText;
import com.focusings.focusingsworld.ImageAndTextList.ImageAndTextListAdapter;
import com.focusings.focusingsworld.pullToRefreshLibrary.PullToRefreshListView;
import com.focusings.focusingsworld.pullToRefreshLibrary.PullToRefreshYoutubeOnRefreshListener;

public class AsyncYoutubeParser extends AsyncTask<String, Integer,List<ImageAndText>> {

	private int tabNumber=1;
	private boolean needToCallOnRefreshComplete=false;
	private MainActivity mainActivity;
	
	public AsyncYoutubeParser(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	
	@Override
	protected List<ImageAndText> doInBackground(String... params) {
		//List<ImageAndText> list=AsyncYoutubeParserGeneral.doInBackgroundGeneral(params);
		List<ImageAndText> l1=new ArrayList<ImageAndText>();
		
		try{
			if (params[0].contains(MainActivity.properties.getProperty("tab_1_channel_name"))){
				tabNumber=1;
			}
			if (params[0].contains(MainActivity.properties.getProperty("tab_2_channel_name"))){
				tabNumber=2;
			}
			if (params[1].equals("true")){
				needToCallOnRefreshComplete=true;
			}
				
			URL url = new URL(params[0]);
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document doc = db.parse(new InputSource(url.openStream()));
	        doc.getDocumentElement().normalize();
	
	        NodeList nodeList = doc.getElementsByTagName("entry");
	
	        //Get first item and save it in lastUpdatePerChannel variable
	        Node firstNode = nodeList.item(0);		            
            NodeList firstNodeListEntry=firstNode.getChildNodes();
            //I get the node that has the id
            Node IdNode=firstNodeListEntry.item(0);
            String urlLastVideo=IdNode.getTextContent();            
            Date publishingDateLastVideo = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(firstNodeListEntry.item(1).getTextContent());
        	VideoInfo vi=new VideoInfo(urlLastVideo, publishingDateLastVideo);
        	
        	//I create the MainActivity.lastUpdatePerChannel in the proper order
        	MainActivity.lastUpdatePerChannel[tabNumber-1]=vi;
	        
	        for (int i = 0; i < nodeList.getLength() && i<10; i++) {
	
	            Node node = nodeList.item(i);
	            
	            NodeList nodeListEntry=node.getChildNodes();
	            String currentTitle=new String();
	            String currentImage=new String();
	            String currentUrl=new String();
	            boolean foundLink=false;
	            
	            for (int j=0;j<nodeListEntry.getLength();j++){
	            	Node currentNode=nodeListEntry.item(j);	            	
	            	if (currentNode.getNodeName().equals("title")){
	            		currentTitle=currentNode.getTextContent();
	            	}
	            	if (!foundLink && currentNode.getNodeName().equals("link")){
	            		foundLink=true;
	            		
	            		NamedNodeMap nnm=currentNode.getAttributes();
	            		Node nodeUrl=nnm.getNamedItem("href");
	            		currentUrl=nodeUrl.getTextContent();
	            	}
	            	if (currentNode.getNodeName().equals("media:group")){
	            		NodeList nodeListMediaGroup=currentNode.getChildNodes();
	            		boolean found=false;
	            		for (int k=0;k<nodeListMediaGroup.getLength()&&!found;k++){
	            			Node currentNodeMediaGroup =nodeListMediaGroup.item(k);
	    	            	if (currentNodeMediaGroup.getNodeName().equals("media:thumbnail")){
	    	            		found=true;
	    	            		NamedNodeMap nnm=currentNodeMediaGroup.getAttributes();
	    	            		Node nodeImageUrl=nnm.getNamedItem("url");
	    	            		currentImage=nodeImageUrl.getTextContent();
	    	            	}	            			
	            		}	            	
	            	}
	            }

	            ImageAndText i1= new ImageAndText(currentImage, currentTitle,currentUrl);
	            l1.add(i1); 
		   }
	    } catch (Exception e) {
	        System.out.println("XML Pasing Exception = " + e);
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