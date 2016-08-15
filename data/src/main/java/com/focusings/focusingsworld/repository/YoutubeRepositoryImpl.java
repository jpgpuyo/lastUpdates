package com.focusings.focusingsworld.repository;

import com.focusings.focusingsworld.bo.ImageAndText;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by usuario on 14/08/2016.
 */
public class YoutubeRepositoryImpl implements YoutubeRepository {

    @Override
    public Observable<List<ImageAndText>> getLastVideosFromChannel() {
        List<ImageAndText> videosList = new ArrayList<ImageAndText>();
        videosList.add(new ImageAndText("https://i.ytimg.com/vi/a46Me73ykcQ/default.jpg", "¿ALGUNA VEZ ME HA GUSTADO UNA CHICA? #focurrespuestas", "https://www.youtube.com/watch?v=a46Me73ykcQ"));
        videosList.add(new ImageAndText("https://i.ytimg.com/vi/W1-w17bTv7s/default.jpg", "LAS SOBRAS DE NAVIDAD PARA MÍ!!! #focumail", "https://www.youtube.com/watch?v=W1-w17bTv7s"));
        videosList.add(new ImageAndText("https://i.ytimg.com/vi/779eElf2fS0/default.jpg", "RESPUESTAS PRÁCTICAS A PREGUNTAS ABSURDAS #ad", "https://www.youtube.com/watch?v=779eElf2fS0"));
        videosList.add(new ImageAndText("https://i.ytimg.com/vi/fvPN69XrVi8/default.jpg", "SECTAS COMERCIALES. Cómo me captaron y cómo conseguí escapar", "https://www.youtube.com/watch?v=fvPN69XrVi8"));
        videosList.add(new ImageAndText("https://i.ytimg.com/vi/jzpTXfKTs2M/default.jpg", "FRASES DE MADRE EN LA PLAYA", "https://www.youtube.com/watch?v=jzpTXfKTs2M"));
        return Observable.from(videosList).toList();
    }
}
