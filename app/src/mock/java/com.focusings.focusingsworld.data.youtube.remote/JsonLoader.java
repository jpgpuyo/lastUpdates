package com.focusings.focusingsworld.data.youtube.remote;

import android.content.Context;
import android.support.annotation.RawRes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class JsonLoader {

    public static String loadJson(Context context, @RawRes int resource) {
        String json;
        try {
            InputStream is = context.getResources().openRawResource(resource);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, Charset.forName("UTF-8"));

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}
