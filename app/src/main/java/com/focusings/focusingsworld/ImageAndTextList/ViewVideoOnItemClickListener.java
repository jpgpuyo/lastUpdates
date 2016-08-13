package com.focusings.focusingsworld.ImageAndTextList;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

public class ViewVideoOnItemClickListener implements OnClickListener{

	private String url;
	
    public ViewVideoOnItemClickListener(String url){
        this.url=url;
    }

	@Override
	public void onClick(View v) {
    	v.getContext().startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url)));
	}
  
}