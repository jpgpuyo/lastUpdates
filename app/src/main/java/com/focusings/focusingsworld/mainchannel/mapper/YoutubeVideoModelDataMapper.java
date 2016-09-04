package com.focusings.focusingsworld.mainchannel.mapper;

import com.focusings.focusingsworld.bo.ImageAndText;
import com.focusings.focusingsworld.mainchannel.model.YoutubeVideoModel;

import java.util.ArrayList;
import java.util.List;

public class YoutubeVideoModelDataMapper {

    public List<YoutubeVideoModel> transform(List<ImageAndText> youtubeVideosCollection){
        List<YoutubeVideoModel> youtubeVideoModelsCollection = new ArrayList<>();
        for (ImageAndText youtubeVideo : youtubeVideosCollection) {
            youtubeVideoModelsCollection.add(transform(youtubeVideo));
        }
        return youtubeVideoModelsCollection;
    }

    private YoutubeVideoModel transform(ImageAndText youtubeVideo) {
        if (youtubeVideo == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        YoutubeVideoModel youtubeVideoModel = new YoutubeVideoModel();
        youtubeVideoModel.setTitle(youtubeVideo.getText());
        youtubeVideoModel.setImage(youtubeVideo.getImageUrl());
        youtubeVideoModel.setUrl(youtubeVideo.getUrl());
       return youtubeVideoModel;
    }
}
