package com.focusings.focusingsworld5.ImageAndTextList;

import com.focusings.focusingsworld5.R;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

public class ShareVideoOnItemClickListener implements OnClickListener{

	private String url;
	private String title;
	
    public ShareVideoOnItemClickListener(String url,String title){
        this.url=url;
        this.title=title;
    }

	@Override
	public void onClick(View v) {
		StringBuffer textToSend=new StringBuffer();
		textToSend.append(v.getContext().getResources().getText(R.string.shareVideoRecommendation));
		textToSend.append(" ");
		textToSend.append("\"");
		textToSend.append(title);
		textToSend.append("\"");
		textToSend.append(" - ");
		textToSend.append(url);
				
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, new String(textToSend));
		sendIntent.setType("text/plain");
		v.getContext().startActivity(Intent.createChooser(sendIntent, v.getContext().getResources().getText(R.string.shareVideoVia)));
		
		
		
    	//v.getContext().startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url)));
	}
  
}