package com.focusings.focusingsworld.data.youtube.mock;

import com.focusings.focusingsworld.domain.models.YoutubeVideo;
import com.focusings.focusingsworld.domain.repository.YoutubeRepository;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public final class YoutubeMockDataStore implements YoutubeRepository {

    public YoutubeMockDataStore() {
    }

    @Override
    public Observable<List<YoutubeVideo>> refreshVideos(String channelId) {
        return Observable.empty();
    }

    @Override
    public Observable<List<YoutubeVideo>> getVideos() {
        List<YoutubeVideo> videosList = new ArrayList<YoutubeVideo>();
        videosList.add(new YoutubeVideo("https://i.ytimg.com/vi/a46Me73ykcQ/default.jpg", "¿ALGUNA VEZ ME HA GUSTADO UNA CHICA? #focurrespuestas", "https://www.youtube.com/watch?v=a46Me73ykcQ"));
        videosList.add(new YoutubeVideo("https://i.ytimg.com/vi/W1-w17bTv7s/default.jpg", "LAS SOBRAS DE NAVIDAD PARA MÍ!!! #focumail", "https://www.youtube.com/watch?v=W1-w17bTv7s"));
        videosList.add(new YoutubeVideo("https://i.ytimg.com/vi/779eElf2fS0/default.jpg", "RESPUESTAS PRÁCTICAS A PREGUNTAS ABSURDAS #ad", "https://www.youtube.com/watch?v=779eElf2fS0"));
        videosList.add(new YoutubeVideo("https://i.ytimg.com/vi/fvPN69XrVi8/default.jpg", "SECTAS COMERCIALES. Cómo me captaron y cómo conseguí escapar", "https://www.youtube.com/watch?v=fvPN69XrVi8"));
        videosList.add(new YoutubeVideo("https://i.ytimg.com/vi/jzpTXfKTs2M/default.jpg", "FRASES DE MADRE EN LA PLAYA", "https://www.youtube.com/watch?v=jzpTXfKTs2M"));
        return Observable.from(videosList).toList();
    }
}
