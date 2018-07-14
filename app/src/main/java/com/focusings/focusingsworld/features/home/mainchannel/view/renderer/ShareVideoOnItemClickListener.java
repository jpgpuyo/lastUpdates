package com.focusings.focusingsworld.features.home.mainchannel.view.renderer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.focusings.focusingsworld.R;

public class ShareVideoOnItemClickListener implements OnClickListener {

    private String title;
    private String url;

    public ShareVideoOnItemClickListener(String title, String url) {
        this.title = title;
        this.url = url;
    }

    @Override
    public void onClick(View view) {
        if (view.getContext() != null) {
            String textToSend = getTextToSend(view.getContext());
            Intent shareIntent = getShareIntent(textToSend);
            Intent chooserIntent = getIntentChooser(view, shareIntent);
            view.getContext().startActivity(chooserIntent);
        }
    }

    private String getTextToSend(Context context) {
        StringBuffer textToSend = new StringBuffer();
        textToSend.append(context.getResources().getString(R.string.shareVideoRecommendation, title, url));
        return textToSend.toString();
    }

    private Intent getShareIntent(String textToSend) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, textToSend);
        sendIntent.setType("text/plain");
        return sendIntent;
    }

    private Intent getIntentChooser(View view, Intent shareIntent) {
        return Intent.createChooser(shareIntent, view.getContext().getResources().getText(R.string.shareVideoVia));
    }

}