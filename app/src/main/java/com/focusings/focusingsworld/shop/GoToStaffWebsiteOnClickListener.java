package com.focusings.focusingsworld.shop;

import com.focusings.focusingsworld.R;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

public class GoToStaffWebsiteOnClickListener implements OnClickListener{

    public GoToStaffWebsiteOnClickListener(){}

	@Override
	public void onClick(View v) {
		String url = "http://es.qstoms.com/fb/focusingsshop";
		Intent goToStaffWebsiteIntent = new Intent(Intent.ACTION_VIEW);
		goToStaffWebsiteIntent.setData(Uri.parse(url));
		v.getContext().startActivity(Intent.createChooser(goToStaffWebsiteIntent, v.getContext().getResources().getText(R.string.goToTheWebsiteThrough)));		
	}
  
}