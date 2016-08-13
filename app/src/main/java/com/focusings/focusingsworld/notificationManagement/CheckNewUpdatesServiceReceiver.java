package com.focusings.focusingsworld.notificationManagement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CheckNewUpdatesServiceReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Intent checkNewUpdates = new Intent(context, CheckNewUpdatesService.class);
		context.startService(checkNewUpdates);	    
	}
}