package com.jehandadk.oriontest.data.api;

import com.google.gson.Gson;

/**
 * Created by jehandad.kamal on 6/1/2016.
 */
public class Factory {

    private static Gson gson;

    public static Gson gson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }
}
