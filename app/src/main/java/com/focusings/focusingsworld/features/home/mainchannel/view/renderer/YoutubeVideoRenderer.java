package com.focusings.focusingsworld.features.home.mainchannel.view.renderer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YoutubeVideoRenderer extends RecyclerView.ViewHolder {

    private final View view;

    @BindView(R.id.tvTitle)
    TextView title;

    @BindView(R.id.ivImage)
    ImageView image;

    @BindView(R.id.share)
    Button share;

    protected View getView() {
        return view;
    }

    public YoutubeVideoRenderer(@NonNull View view) {
        super(view);
        this.view = view;
        ButterKnife.bind(this, view);
    }

    public void render(@NonNull YoutubeVideo youtubeVideo) {
        renderTitle(youtubeVideo);
        renderImage(youtubeVideo);
    }

    private void renderTitle(@NonNull YoutubeVideo youtubeVideo) {
        title.setText(youtubeVideo.getTitle());
    }

    private void renderImage(@NonNull YoutubeVideo youtubeVideo) {
        Glide.with(image.getContext()).load(youtubeVideo.getImage()).into(image);
    }

    public void setListeners(@NonNull YoutubeVideo youtubeVideo) {
        setPlayVideoListener(youtubeVideo);
        setShareVideoListener(youtubeVideo);
    }

    private void setShareVideoListener(@NonNull YoutubeVideo youtubeVideo) {
        share.setOnClickListener(new ShareVideoOnItemClickListener(youtubeVideo.getTitle(), youtubeVideo.getUrl()));
    }

    private void setPlayVideoListener(@NonNull YoutubeVideo youtubeVideo) {
        view.setOnClickListener(new PlayVideoOnClickListener(youtubeVideo.getUrl()));
    }
}
