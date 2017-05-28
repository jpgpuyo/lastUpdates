package com.focusings.focusingsworld.presentation.mainchannel.view.renderer.youtubevideo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.presentation.mainchannel.model.YoutubeVideoModel;
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

    public void setListeners(@NonNull YoutubeVideoModel youtubeVideoModel) {
        setPlayVideoListener(youtubeVideoModel);
        setShareVideoListener(youtubeVideoModel);
    }

    private void setShareVideoListener(@NonNull YoutubeVideoModel youtubeVideoModel) {
        share.setOnClickListener(new ShareVideoOnItemClickListener(youtubeVideoModel.getTitle(), youtubeVideoModel.getUrl()));
    }

    private void setPlayVideoListener(@NonNull YoutubeVideoModel youtubeVideoModel) {
        view.setOnClickListener(new PlayVideoOnClickListener(youtubeVideoModel.getUrl()));
    }
}
