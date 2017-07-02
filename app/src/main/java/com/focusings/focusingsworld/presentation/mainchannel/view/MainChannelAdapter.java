package com.focusings.focusingsworld.presentation.mainchannel.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.domain.models.YoutubeVideo;
import com.focusings.focusingsworld.infrastructure.renderer.ItemsList;
import com.focusings.focusingsworld.presentation.mainchannel.view.renderer.youtubevideo.YoutubeVideoRenderer;

import java.util.List;

public class MainChannelAdapter extends RecyclerView.Adapter<YoutubeVideoRenderer> {

    private final ItemsList<YoutubeVideo> items;

    public MainChannelAdapter() {
        this.items = new ItemsList(this);
    }

    public void clear() {
        items.clear();
    }

    public void addAll(List<YoutubeVideo> youtubeVideoList) {
        items.addAll(youtubeVideoList);
    }

    @Override
    public YoutubeVideoRenderer onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.youtube_item,
                viewGroup, false);
        return new YoutubeVideoRenderer(view);
    }

    @Override
    public void onBindViewHolder(YoutubeVideoRenderer youtubeVideoRenderer, int position) {
        YoutubeVideo youtubeVideo = items.get(position);
        youtubeVideoRenderer.render(youtubeVideo);
        youtubeVideoRenderer.setListeners(youtubeVideo);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

