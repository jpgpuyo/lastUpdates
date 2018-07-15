package com.focusings.focusingsworld.utils.testfiles;

import com.google.gson.Gson;

import java.io.IOException;

public class JsonReader<T> {

    public T fromJson(String filename, Class clazz) throws IOException {
        String body = FileExtensions.getStringFromFile(clazz, filename);
        T response = new Gson().fromJson(body, (Class<T>)clazz);
        return response;
    }
}
