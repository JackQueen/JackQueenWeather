package com.jackqueenweather.android.app;

import android.content.Context;

import org.litepal.LitePalApplication;

/**
 * Created by Administrator on 2016-12-22.
 */

public class MyApplication  extends LitePalApplication{
    private static Context context;

    @Override
    public void onCreate() {
        context=getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
