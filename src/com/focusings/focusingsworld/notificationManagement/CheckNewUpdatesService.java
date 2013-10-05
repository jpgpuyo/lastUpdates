package com.focusings.focusingsworld.notificationManagement;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.focusings.focusingsworld.MainActivity;
import com.focusings.focusingsworld.VideoInfo;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;

public class CheckNewUpdatesService extends IntentService {

	public static AsyncNotificationResponse delegate=null;
	
	public CheckNewUpdatesService() {
		super("CheckNewUpdatesService");
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		new CheckNewUpdatesTask().execute();
	}
	
	private class CheckNewUpdatesTask extends AsyncTask<String, Void, List<Update>> {
		@Override
		protected List<Update> doInBackground(String... strings) {
			
			List<Update> lu= new LinkedList<Update>();
			try{
			
				//Check if there is any update in any of the channels
				int numberOfTabs=Integer.parseInt(MainActivity.properties.getProperty("number_of_tabs"));
				for (int i=0;i<numberOfTabs;i++){				
					URL url = new URL(MainActivity.properties.getProperty("Youtube_URL_part_1")+MainActivity.properties.getProperty("tab_"+(i+1)+"_channel_name")+MainActivity.properties.getProperty("Youtube_URL_part_2"));
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();
					Document doc = db.parse(new InputSource(url.openStream()));
					doc.getDocumentElement().normalize();
			
			        NodeList nodeList = doc.getElementsByTagName("entry");
			
			        //Go to the first item
			        Node node = nodeList.item(0);
			            
		            NodeList nodeListEntry=node.getChildNodes();
		            
		            //I get the node that has the id
		            Node firstNode=nodeListEntry.item(0);
		            String lastUrl=firstNode.getTextContent();
		            
		            boolean foundTitle=false;
		            
		            //Case MainActivity.lastUpdatePerChannel not initialized
		            if (MainActivity.lastUpdatePerChannel[i]==null){
		            	VideoInfo vi=new VideoInfo(lastUrl, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(nodeListEntry.item(1).getTextContent()));
		            	MainActivity.lastUpdatePerChannel[i]=vi;
		            }
		            
		            //If lastId is not the last one, then I look for the title of the video and send a notification
		            if (lastUrl!=null && MainActivity.lastUpdatePerChannel[i]!=null && !lastUrl.equals(MainActivity.lastUpdatePerChannel[i].getUrl())){
		            			            	
		            	//Check if lastId is newer than the one in MainActivity.lastUpdatePerChannel
		            	Date publishingDateNewVideo = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(nodeListEntry.item(1).getTextContent());
		            	Date publishingDateOldVideo = MainActivity.lastUpdatePerChannel[i].getPublishingDate();
		            	
		            	//If new video is newer than the previous one, then I should send a notification
		            	//Otherwise, it may be the youtuber has deleted the last video, so we
		            	//shouldn't send the notification
		            	if (publishingDateNewVideo.after(publishingDateOldVideo)){		            		
			            	//First, I look for the new title
			            	//Send notification
			            	for (int j=0;j<nodeListEntry.getLength() && !foundTitle;j++){
				            	Node currentNode=nodeListEntry.item(j);
				            	if (currentNode.getNodeName().equals("title")){
				            		foundTitle=true;
				            		Update u=new Update(currentNode.getTextContent(),
				            				MainActivity.properties.getProperty("tab_"+(i+1)+"_channel_public_name"),
				            				i);
				            		lu.add(u);
				            	}
				            }
		            	}
			            	
		            	//In both cases, I update the lastUpdatePerChannel
		            	MainActivity.lastUpdatePerChannel[i].setUrl(lastUrl);		            			            	
		            	MainActivity.lastUpdatePerChannel[i].setPublishingDate(publishingDateNewVideo);		            	
		            }
				}
			}catch (Exception e) {
		        System.out.println("XML Pasing Exception = " + e);
		    }
			//If there is any update, then call sendNotification
			return lu;
		}
		
		@Override
		protected void onPostExecute(List<Update> updates) {
			if (updates!=null && updates.size()>0){
				//Send notification
				delegate.sendNotification(updates);
			}
		}
	}
}