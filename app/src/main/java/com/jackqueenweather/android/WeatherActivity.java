package com.jackqueenweather.android;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.jackqueenweather.android.databinding.ForecastBinding;
import com.jackqueenweather.android.databinding.WeatherBinding;
import com.jackqueenweather.android.model_gson.Weather;
import com.jackqueenweather.android.util.GsonUtil;
import com.jackqueenweather.android.util.HttpUtil;
import com.jackqueenweather.android.util.SPUtil;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    @BindView(R.id.ll_forecast_layout)
    LinearLayout llForecastLayout;
    @BindView(R.id.scrollv_weather_layout)
    ScrollView scrollvWeatherLayout;
    private WeatherBinding weatherBinding;
    private Weather.HeWeatherBean heWeatherBean;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_weather);
        weatherBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        unbinder = ButterKnife.bind(this);
        String weatherInfo = SPUtil.getWeather(WeatherActivity.this);
        if (weatherInfo != null) {
            //有缓存则直接解析
            Weather weather = GsonUtil.parseWeatherFrom(weatherInfo);
            showWeatherInfo(weather);
        } else {
            //无缓存到服务器查询
            String weatherId = getIntent().getStringExtra("weather_id");
            scrollvWeatherLayout.setVisibility(View.INVISIBLE);
            requesWeather(weatherId);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * 根据地区的天气id请求天气信息
     */
    private void requesWeather(final String weatherId) {
        String weatherUrl = Constant.WEATHER_HOST + weatherId + Constant.WEATHER_KEY;
        HttpUtil.getRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = GsonUtil.parseWeatherFrom(responseText);
                //在主线程保存天气信息到本地共享参数
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //如果请求成功
                        if (weather != null && "ok".equals(weather.getHeWeather().get(0).getStatus())) {
                            SPUtil.saveWeather(WeatherActivity.this, responseText);
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void showWeatherInfo(Weather weather) {
        heWeatherBean = weather.getHeWeather().get(0);
        Weather.HeWeatherBean.Now now = heWeatherBean.getNow();
        Weather.HeWeatherBean.Basic basic = heWeatherBean.getBasic();
        Weather.HeWeatherBean.Suggestion suggestion = heWeatherBean.getSuggestion();
        Weather.HeWeatherBean.AQI aqi = heWeatherBean.getAqi();
        List<Weather.HeWeatherBean.DailyForecast> daily_forecast = heWeatherBean.getDaily_forecast();
        weatherBinding.setNow(now);
        weatherBinding.setBasic(basic);
        weatherBinding.setAqi(aqi);
        weatherBinding.setSuggestion(suggestion);
        llForecastLayout.removeAllViews();
        for (int i = 0; i < daily_forecast.size(); i++) {
            View view = LayoutInflater.from(WeatherActivity.this).inflate(R.layout.forecast_item, llForecastLayout, false);
            Weather.HeWeatherBean.DailyForecast forecast = daily_forecast.get(i);
            ForecastBinding bind = DataBindingUtil.bind(view);
            llForecastLayout.addView(view);
            bind.setForecast(forecast);
        }
        scrollvWeatherLayout.setVisibility(View.VISIBLE);
    }
}
