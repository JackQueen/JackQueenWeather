package com.jackqueenweather.android;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.jackqueenweather.android.databinding.ForecastBinding;
import com.jackqueenweather.android.databinding.WeatherBinding;
import com.jackqueenweather.android.model_gson.Weather;
import com.jackqueenweather.android.service.AutoUpdateService;
import com.jackqueenweather.android.util.GsonUtil;
import com.jackqueenweather.android.util.HttpUtil;
import com.jackqueenweather.android.util.ImageUtil;
import com.jackqueenweather.android.util.SPUtil;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    @BindView(R.id.ll_forecast_layout)
    LinearLayout llForecastLayout;
    @BindView(R.id.scrollv_weather_layout)
    ScrollView scrollvWeatherLayout;
    @BindView(R.id.iv_bg_pic)
    ImageView ivBgPic;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.btn_show_area)
    Button btnShowArea;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private WeatherBinding weatherBinding;
    private Weather.HeWeatherBean heWeatherBean;
    private Unbinder unbinder;


    private String weatherId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化沉浸式效果
        initImmerseBar();
        weatherBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        unbinder = ButterKnife.bind(this);
        initWeatherInfo();
        initBackgroundPic();
        initSwipeRefresh();
    }

    private void initSwipeRefresh() {
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requesWeather(weatherId);
            }
        });
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    private void initImmerseBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            // "|"或运算
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);//透明色
        }
    }

    private void initBackgroundPic() {
        String backgPic = SPUtil.getBackgPic(WeatherActivity.this);
        if (backgPic != null) {
            ImageUtil.HelloImage(WeatherActivity.this, backgPic, ivBgPic);
        } else {
            loadingBackPic();
        }
    }

    private void loadingBackPic() {
        HttpUtil.getRequest(Constant.PIC_HOST, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String backgroundPic = response.body().string();
                SPUtil.saveBackPic(WeatherActivity.this, backgroundPic);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ImageUtil.HelloImage(WeatherActivity.this, backgroundPic, ivBgPic);
                    }
                });
            }
        });
    }

    private void initWeatherInfo() {
        String weatherInfo = SPUtil.getWeather(WeatherActivity.this);
        if (weatherInfo != null) {
            //有缓存则直接解析
            Weather weather = GsonUtil.parseWeatherFrom(weatherInfo);
            weatherId = weather.getHeWeather().get(0).getBasic().getWeatherId();
            showWeatherInfo(weather);
        } else {
            //无缓存到服务器查询
            weatherId = getIntent().getStringExtra("weather_id");
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
    public void requesWeather(final String weatherId) {
        String weatherUrl = Constant.WEATHER_HOST + weatherId + Constant.WEATHER_KEY;
        HttpUtil.getRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                        swipeRefresh.setRefreshing(false);
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
                            Intent intent = new Intent(WeatherActivity.this, AutoUpdateService.class);
                            startService(intent);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });

            }
        });
        loadingBackPic();
    }

    /**
     * 显示界面
     */
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

    @OnClick(R.id.btn_show_area)
    public void onClick() {
        drawerLayout.openDrawer(GravityCompat.START);
    }
}
