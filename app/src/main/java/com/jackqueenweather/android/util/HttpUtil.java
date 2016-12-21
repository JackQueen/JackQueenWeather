package com.jackqueenweather.android.util;

import com.jackqueenweather.android.Constant;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2016-12-21.
 */

public class HttpUtil {

    public static void getRequest(String url, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }

    public static void getRequestWithId(String weatherId, okhttp3.Callback callback) {
        String url = Constant.WEATHER_HOST + weatherId + Constant.WEATHER_KEY;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }
}
