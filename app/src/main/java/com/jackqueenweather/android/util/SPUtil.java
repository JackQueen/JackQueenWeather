package com.jackqueenweather.android.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016-12-21.
 */

public class SPUtil {

    /**
     * 保存天气信息
     */
    public static void saveWeather(Context context, String string) {
        SharedPreferences.Editor editor = context.getSharedPreferences("weather_info", Context.MODE_PRIVATE).edit();
        //// TODO: 2016-12-21 注意区别
//        editor.putString("weather", string).apply();
        editor.putString("weather", string).commit();

    }

    /**
     * 获取天气信息
     */
    public static String getWeather(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("weather_info", Context.MODE_PRIVATE);
        String weather = preferences.getString("weather", null);
        return weather;
    }

    /**
     * 获取背景图片信息
     **/
    public static String getBackgPic(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("weather_info", Context.MODE_PRIVATE);
        return preferences.getString("bing_pic", null);
    }

    /**
     * 保存背景图片信息
     * */
    public static void saveBackPic(Context context, String string) {
        SharedPreferences.Editor editor = context.getSharedPreferences("weather_info", Context.MODE_PRIVATE).edit();
//        editor.putString("bing_pic", string).apply();
        editor.putString("bing_pic", string).commit();
    }
}
