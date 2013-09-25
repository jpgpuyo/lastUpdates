package com.focusings.focusingsworld5.YoutubeParser;

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

import com.focusings.focusingsworld5.YoutubeParser.AsyncResponse;
import com.focusings.focusingsworld5.MainActivity;
import com.focusings.focusingsworld5.VideoInfo;
import com.focusings.focusingsworld5.ImageAndTextList.ImageAndText;

public class AsyncYoutubeParser extends AsyncTask<String, Integer,List<ImageAndText>> {

	public static AsyncResponse delegate=null;
	private int tabNumber=1;
	
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
        	MainActivity.lastUpdatePerChannel.add(vi);
	        
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
		delegate.processFinish(result,tabNumber);	
	}
}