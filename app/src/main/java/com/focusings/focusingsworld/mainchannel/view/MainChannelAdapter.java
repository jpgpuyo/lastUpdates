package com.focusings.focusingsworld.mainchannel.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.mainchannel.model.YoutubeVideoModel;

import java.util.List;

public class MainChannelAdapter extends RecyclerView.Adapter<MainChannelAdapter.YoutubeVideoViewHolder> {

    List<YoutubeVideoModel> youtubeVideoCollection;

    public MainChannelAdapter() {}

    public void setYoutubeVideoCollection(List<YoutubeVideoModel> youtubeVideosCollection) {
        this.youtubeVideoCollection = youtubeVideosCollection;
    }

    @Override
    public YoutubeVideoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.youtube_item,
                viewGroup, false);
        return new YoutubeVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YoutubeVideoViewHolder youtubeVideoViewHolder, int position) {
        YoutubeVideoModel youtubeVideoModel = youtubeVideoCollection.get(position);
        youtubeVideoViewHolder.title.setText(youtubeVideoModel.getTitle());
        Glide.with(youtubeVideoViewHolder.image.getContext()).load(youtubeVideoModel.getImage()).into(youtubeVideoViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return youtubeVideoCollection == null ? 0 : youtubeVideoCollection.size();
    }

    class YoutubeVideoViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView image;

        public YoutubeVideoViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tvTitle);
            image = (ImageView) itemView.findViewById(R.id.ivImage);
        }
    }

}

