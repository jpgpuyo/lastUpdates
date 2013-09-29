package com.focusings.focusingsworld5.ImageAndTextList;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import com.focusings.focusingsworld5.R;
import com.focusings.focusingsworld5.ImageAndTextList.AsyncImageLoader.ImageCallback;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ImageAndTextListAdapter extends ArrayAdapter<ImageAndText>{

	private AsyncImageLoader asyncImageLoader;
	
	
    public ImageAndTextListAdapter(Activity activity, List<ImageAndText> imageAndTexts) {
        super(activity, 0, imageAndTexts);
        asyncImageLoader = new AsyncImageLoader();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = (Activity) getContext();
        LayoutInflater inflater = activity.getLayoutInflater();

        // Inflate the views from XML
        View rowView = inflater.inflate(R.layout.image_and_text_row, null);
        ImageAndText imageAndText = getItem(position);

        // Load the image and set it on the ImageView
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
        Drawable cachedImage = asyncImageLoader.loadDrawable(imageAndText.getImageUrl(), new ImageCallback() {
            public void imageLoaded(Drawable imageDrawable, String imageUrl) {
                imageView.setImageDrawable(imageDrawable);
            }
        });
        imageView.setImageDrawable(cachedImage);

        // Set the text on the TextView
        TextView textView = (TextView) rowView.findViewById(R.id.text);
        textView.setText(imageAndText.getText());
        
        //When clicking View button, go to watch Youtube video
        Button viewButton = (Button) rowView.findViewById(R.id.viewButton);
        ViewVideoOnItemClickListener v1= new ViewVideoOnItemClickListener(imageAndText.getUrl());
        viewButton.setOnClickListener(v1);
        //When clicking on the image of the video, also go to watch Youtube video
        imageView.setOnClickListener(v1);
        
        //When clicking View button, go to watch Youtube video
        Button shareButton = (Button) rowView.findViewById(R.id.shareButton);
        ShareVideoOnItemClickListener s1= new ShareVideoOnItemClickListener(imageAndText.getUrl(),imageAndText.getText());
        shareButton.setOnClickListener(s1);
        
        return rowView;
    }

    public static Drawable loadImageFromUrl(String url) {
        InputStream inputStream;
        try {
            inputStream = new URL(url).openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Drawable.createFromStream(inputStream, "src");
    }
  
}