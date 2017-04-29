package com.focusings.focusingsworld.mainchannel.view.renderer.youtubevideo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.mainchannel.model.YoutubeVideoModel;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class YoutubeVideoViewHolder extends RecyclerView.ViewHolder {

    private final View rootView;
    private final OnViewHolderClickListener listener;

    @InjectView(R.id.tvTitle)
    TextView title;

    @InjectView(R.id.ivImage)
    ImageView image;

    protected View getView() {
        return rootView;
    }

    public YoutubeVideoViewHolder(@NonNull View view, @NonNull OnViewHolderClickListener listener) {
        super(view);
        this.rootView = view;
        this.listener = listener;
        ButterKnife.inject(this, view);
        initializeListeners();
    }

    private void initializeListeners() {
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                listener.onViewHolderClick(YoutubeVideoViewHolder.this, position);
            }
        });
    }

    public void render(@NonNull YoutubeVideoModel youtubeVideoModel) {
        renderTitle(youtubeVideoModel);
        renderImage(youtubeVideoModel);
    }

    private void renderTitle(@NonNull YoutubeVideoModel youtubeVideoModel) {
        title.setText(youtubeVideoModel.getTitle());
    }

    private void renderImage(@NonNull YoutubeVideoModel youtubeVideoModel) {
        Glide.with(image.getContext()).load(youtubeVideoModel.getImage()).into(image);
    }

    public interface OnViewHolderClickListener {
        void onViewHolderClick(RecyclerView.ViewHolder holder, int position);
    }
}
