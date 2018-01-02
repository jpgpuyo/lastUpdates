package unit.youtube.infrastructure;


import com.focusings.focusingsworld.data.youtube.core.api.dto.YoutubeVideoDto;
import com.focusings.focusingsworld.data.youtube.core.api.dto.recentvideos.RecentVideosResponseDto;

import java.io.IOException;

public class RecentVideosMother {

    public static YoutubeVideoDto givenFirstVideoFromJson() throws IOException {
        JsonReader<RecentVideosResponseDto> jsonReader = new JsonReader<>();
        RecentVideosResponseDto expectedRecentVideosResponseDto = jsonReader.fromJson("getRecentVideosFromChannel.json", RecentVideosResponseDto.class);
        return expectedRecentVideosResponseDto.getItems().get(0);
    }
}
