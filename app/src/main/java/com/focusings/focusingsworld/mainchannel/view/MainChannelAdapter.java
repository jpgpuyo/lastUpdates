package com.focusings.focusingsworld.mainchannel.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.mainchannel.model.YoutubeVideoModel;
import com.focusings.focusingsworld.base.renderer.ItemsList;
import com.focusings.focusingsworld.mainchannel.view.renderer.youtubevideo.YoutubeVideoViewHolder;

import java.util.List;

public class MainChannelAdapter extends RecyclerView.Adapter<YoutubeVideoViewHolder> {

    private final ItemsList<YoutubeVideoModel> items;

    public MainChannelAdapter() {
        this.items = new ItemsList(this);
    }

    public void clear() {
        items.clear();
    }

    public void addAll(List<YoutubeVideoModel> youtubeVideoModelList) {
        items.addAll(youtubeVideoModelList);
    }

    @Override
    public YoutubeVideoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.youtube_item,
                viewGroup, false);
        return new YoutubeVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YoutubeVideoViewHolder youtubeVideoViewHolder, int position) {
        YoutubeVideoModel youtubeVideoModel = items.get(position);
        youtubeVideoViewHolder.render(youtubeVideoModel);
        youtubeVideoViewHolder.setListeners(youtubeVideoModel);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

