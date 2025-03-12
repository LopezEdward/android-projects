package dev.edwlopez.android.finalproject.data;

import android.content.Context;

import retrofit2.Retrofit;

public abstract class AbstractRestAPI {
    protected static String SERVER_URL;
    protected Retrofit serviceManager;

    public static void setAPIUrl (String url) {
        SERVER_URL = url;
    }
}
