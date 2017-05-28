package com.focusings.focusingsworld.presentation.mainchannel.presenter;

import com.focusings.focusingsworld.domain.models.YoutubeVideo;
import com.focusings.focusingsworld.presentation.mainchannel.model.YoutubeVideoModel;

import java.util.ArrayList;
import java.util.List;

public class YoutubeVideoModelMapper {

    public List<YoutubeVideoModel> transform(List<YoutubeVideo> youtubeVideosCollection){
        List<YoutubeVideoModel> youtubeVideoModelsCollection = new ArrayList<>();
        for (YoutubeVideo youtubeVideo : youtubeVideosCollection) {
            youtubeVideoModelsCollection.add(transform(youtubeVideo));
        }
        return youtubeVideoModelsCollection;
    }

    private YoutubeVideoModel transform(YoutubeVideo youtubeVideo) {
        if (youtubeVideo == null) {
            throw new IllegalArgumentException("Cannot parse a null value");
        }

        YoutubeVideoModel youtubeVideoModel = new YoutubeVideoModel();
        youtubeVideoModel.setId(youtubeVideo.hashCode());
        youtubeVideoModel.setTitle(youtubeVideo.getTitle());
        youtubeVideoModel.setImage(youtubeVideo.getImage());
        youtubeVideoModel.setUrl(youtubeVideo.getUrl());
       return youtubeVideoModel;
    }
}
