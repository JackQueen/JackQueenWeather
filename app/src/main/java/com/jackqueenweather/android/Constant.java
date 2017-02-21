package com.jackqueenweather.android;

/**
 * Created by Administrator on 2016-12-21.
 */

public interface Constant {

        final static String AREA_HOST = "http://guolin.tech/api/china/";
        final static String WEATHER_HOST  = "http://guolin.tech/api/weather?cityid=";
        final static String WEATHER_KEY = "&key=c6f323301c60497ba305501860aafebf";
        final static String PIC_HOST = "http://guolin.tech/api/bing_pic";
//        final static String ICON_HOST = "http://files.heweather.com/cond_icon/%s.png";
        final static String ICON_HOST = "http://jackqueen.oss-cn-shanghai.aliyuncs.com/weather/%s.png";

        String HE_WEATHER_HOST = "https://free-api.heweather.com/v5/";
        String HOURLY_FORECAST = HE_WEATHER_HOST + "hourly/city=%s&key=c6f323301c60497ba305501860aafebf";
        String DAILY_FORECAST = HE_WEATHER_HOST + "forecast/city=%s&key=c6f323301c60497ba305501860aafebf";

}
