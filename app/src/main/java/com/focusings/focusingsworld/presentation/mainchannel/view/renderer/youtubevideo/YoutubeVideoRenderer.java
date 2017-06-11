package com.focusings.focusingsworld.presentation.mainchannel.view.renderer.youtubevideo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.domain.models.YoutubeVideo;
import com.focusings.focusingsworld.presentation.mainchannel.view.renderer.youtubevideo.play.PlayVideoOnClickListener;
import com.focusings.focusingsworld.presentation.mainchannel.view.renderer.youtubevideo.share.ShareVideoOnItemClickListener;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class YoutubeVideoRenderer extends RecyclerView.ViewHolder {

    private final View view;

    @InjectView(R.id.tvTitle)
    TextView title;

    @InjectView(R.id.ivImage)
    ImageView image;

    @InjectView(R.id.share)
    Button share;

    protected View getView() {
        return view;
    }

    public YoutubeVideoRenderer(@NonNull View view) {
        super(view);
        this.view = view;
        ButterKnife.inject(this, view);
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
