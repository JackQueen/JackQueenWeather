package com.jackqueenweather.android.util;

import android.text.TextUtils;

import com.jackqueenweather.android.model_db.City;
import com.jackqueenweather.android.model_db.County;
import com.jackqueenweather.android.model_db.Province;

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
    public static boolean initProvinceJson(String json) {
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
    public static boolean initCityJson(String json,int provinceId) {
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
    public static boolean initCountyJson(String json,int cityId) {
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
}
