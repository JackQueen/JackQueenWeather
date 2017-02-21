package com.jackqueenweather.android.bindingadapter;

import android.app.Application;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.jackqueenweather.android.Constant;
import com.jackqueenweather.android.R;
import com.jackqueenweather.android.app.MyApplication;
import com.jackqueenweather.android.model_gson.Weather;
import com.jackqueenweather.android.util.HttpUtil;
import com.jackqueenweather.android.util.ImageUtil;
import com.jackqueenweather.android.util.SPUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2016-12-22.
 */

public class BindAdapter {

    //BindingAdapter注释的方法不能使用private权限修饰(编译报错) 且需要static修饰(运行报错)
    @BindingAdapter("android:src")
    public static void setSrcs(ImageView view,  String url) {

        ImageUtil.HelloImage(MyApplication.getContext(),url,view);

    }

    @BindingAdapter("android:src")
    public static void setSrcs(ImageView view,  Weather.HeWeatherBean.Now now) {
        if (now == null) {
            return;
        }
        String txt = now.getMore().getTxt();
        switch (txt) {
            case "晴天":
            case "晴":
            case "平静":
            case "和风":
            case "少云":
            case "小雨":
            case "中雨":
            case "大雨":
            case "毛毛雨/细雨":
            case "暴雨":
            case "大暴雨":
                view.setImageResource(R.drawable.bg_moderate_rain_day);
                break;
            case "阴":
            case "多云":
            case "薄雾":
            case "雾":
            case "霾":
            case "沙尘暴":
                view.setImageResource(R.drawable.bg_fog_and_haze);
                break;
        }
    }

    @BindingAdapter("android:drawableRight")
    public static void setIconRight(final TextView view , String code) {
        String iconUrl=String.format(Constant.ICON_HOST, code);
        Log.e("setIconRight", "iconUrl"+iconUrl);
        Glide.with(MyApplication.getContext()).load(iconUrl)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Drawable iconRight = new BitmapDrawable(resource);
                        // 必须设置图片大小，否则不显示
                        iconRight.setBounds(0, 0, 70, 70);
                        view.setCompoundDrawables(null, null, iconRight, null);
                    }
                });
    }

}
