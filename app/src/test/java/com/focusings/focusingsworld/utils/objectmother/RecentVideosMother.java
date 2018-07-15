package com.focusings.focusingsworld.utils.objectmother;


import com.focusings.focusingsworld.data.youtube.core.api.dto.YoutubeVideoDto;
import com.focusings.focusingsworld.data.youtube.core.api.dto.recentvideos.RecentVideosResponseDto;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.data.youtube.recentvideos.RecentVideosDataMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.focusings.focusingsworld.utils.testfiles.JsonReader;

public class RecentVideosMother {

    public static YoutubeVideoDto givenFirstVideoFromJson() throws IOException {
        JsonReader<RecentVideosResponseDto> jsonReader = new JsonReader<>();
        RecentVideosResponseDto expectedRecentVideosResponseDto = jsonReader.fromJson("getRecentVideosFromChannel.json", RecentVideosResponseDto.class);
        return expectedRecentVideosResponseDto.getItems().get(0);
    }

    public static List<YoutubeVideoDto> givenYoutubeVideoListFromJson() throws IOException {
        JsonReader<RecentVideosResponseDto> jsonReader = new JsonReader<>();
        RecentVideosResponseDto expectedRecentVideosResponseDto = jsonReader.fromJson("getRecentVideosFromChannel.json", RecentVideosResponseDto.class);
        return expectedRecentVideosResponseDto.getItems();
    }

    public static List<YoutubeVideo> givenYoutubeVideoListFromDomain() throws IOException {
        JsonReader<RecentVideosResponseDto> jsonReader = new JsonReader<>();
        RecentVideosResponseDto expectedRecentVideosResponseDto = jsonReader.fromJson("getRecentVideosFromChannel.json", RecentVideosResponseDto.class);
        return RecentVideosDataMapper.transform(expectedRecentVideosResponseDto);
    }

    public static List<YoutubeVideo> givenEmptyYoutubeVideoListFromDomain() {
        return new ArrayList<>();
    }
}
