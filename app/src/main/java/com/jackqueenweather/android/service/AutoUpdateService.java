package com.jackqueenweather.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.jackqueenweather.android.Constant;
import com.jackqueenweather.android.model_gson.Weather;
import com.jackqueenweather.android.util.GsonUtil;
import com.jackqueenweather.android.util.HttpUtil;
import com.jackqueenweather.android.util.ImageUtil;
import com.jackqueenweather.android.util.SPUtil;
import com.jackqueenweather.android.util.ToastUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 实现后台自动更新:
 * 1.更新天气信息
 * 2.更新每日背景
 * 3.
 * */
public class AutoUpdateService extends Service {
    public AutoUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        updateWeather();
        updateBackPic();

        //获取Alarm管理器
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //设置Alrm触发时间
        int times = 8 * 60 * 60 * 1000;//8小时的毫秒数
        long triggerTime = SystemClock.elapsedRealtime() + times;//手机开机至今时间+设置的延迟时间
        //设置意图
        Intent itt = new Intent(this, AutoUpdateService.class);//此意图目的在于打开本服务
        PendingIntent pi = PendingIntent.getService(this, 0, itt, 0);
        //设置Alrm任务
        alarmManager.cancel(pi);//如果目前还有延迟意图在队列中,则取消
        //强制精确时间 setExact()和 [Doze模式强制]setExactAndAllowWhileIdle()
        alarmManager.set(AlarmManager.ELAPSED_REALTIME,triggerTime,pi);//重新设置意图任务
        return super.onStartCommand(intent, flags, startId);
    }
    /**
     * 更新天气信息
     * */
    private void updateWeather() {
        String weatherString = SPUtil.getWeather(AutoUpdateService.this);
        if (weatherString != null) {
            //有缓存则直接解析缓存获取id
            Weather weather = GsonUtil.parseWeatherFrom(weatherString);
            String weatherId = weather.getHeWeather().get(0).getBasic().getWeatherId();
            HttpUtil.getRequestWithId(weatherId, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
//                    Log.e("unpdateWeather","您的后台数据更新异常:"+e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body().string();
                    Weather weather = GsonUtil.parseWeatherFrom(responseText);
                    if (weather != null&&"ok".equals(weather.getHeWeather().get(0).getStatus())) {
                        SPUtil.saveWeather(AutoUpdateService.this,responseText);
                    }
                }
            });
        }
    }

    /**
     * 更新背景图片
     * */
    private void updateBackPic() {
        HttpUtil.getRequest(Constant.PIC_HOST, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Log.e("updateBackPic","您的背景图片更新异常:"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String picUrl = response.body().string();
                //因为是在服务中,所以只需要将图片地址保存到本地
                SPUtil.saveBackPic(AutoUpdateService.this,picUrl);
            }
        });
    }
}
