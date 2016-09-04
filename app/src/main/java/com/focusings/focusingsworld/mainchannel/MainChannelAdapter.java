package com.focusings.focusingsworld.mainchannel;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.mainchannel.model.YoutubeVideoModel;

import java.util.List;

public class MainChannelAdapter extends RecyclerView.Adapter<MainChannelAdapter.MyViewHolder> {

    List<YoutubeVideoModel> youtubeVideoCollection;

    public MainChannelAdapter() {}

    public void setYoutubeVideoCollection(List<YoutubeVideoModel> youtubeVideosCollection) {
        this.youtubeVideoCollection = youtubeVideosCollection;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,
                viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        YoutubeVideoModel youtubeVideoModel = youtubeVideoCollection.get(position);
        myViewHolder.title.setText(youtubeVideoModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return youtubeVideoCollection == null ? 0 : youtubeVideoCollection.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.listitem_name);
        }
    }

}

