package com.jackqueenweather.android.util;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.jackqueenweather.android.model_db.City;
import com.jackqueenweather.android.model_db.County;
import com.jackqueenweather.android.model_db.Province;
import com.jackqueenweather.android.model_gson.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016-12-21.
 */

public class GsonUtil {

    /**
     * 解析和处理服务器返回的省级数据
     * */
    public static boolean parseProvinceFrom(String json) {


        if (!TextUtils.isEmpty(json)) {
            try {
                JSONArray provinceArray = new JSONArray(json);

                for (int i = 0; i < provinceArray.length(); i++) {
                    JSONObject provinceObject = provinceArray.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }

                return  true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的城市数据
     * */
    public static boolean parseCityFrom(String json, int provinceId) {
        if (!TextUtils.isEmpty(json)) {

            try {
                JSONArray cityArray = new JSONArray(json);
                for (int i = 0; i < cityArray.length(); i++) {
                    JSONObject cityObject = cityArray.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return  true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }/**
     * 解析和处理服务器返回的县级数据
     * */
    public static boolean parseCountyFrom(String json, int cityId) {
        if (!TextUtils.isEmpty(json)) {

            try {
                JSONArray countyArray = new JSONArray(json);
                for (int i = 0; i < countyArray.length(); i++) {
                    JSONObject cityObject = countyArray.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(cityObject.getString("name"));
                    county.setWeatherId(cityObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return  true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析天气详情信息Json
     * */
    public static Weather parseWeatherFrom(String response) {
        Weather weather = new Gson().fromJson(response, Weather.class);
        return weather;
    }
}
