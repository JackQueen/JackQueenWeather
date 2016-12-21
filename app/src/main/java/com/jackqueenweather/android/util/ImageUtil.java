package com.jackqueenweather.android.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jackqueenweather.android.WeatherActivity;

/**
 * Created by Administrator on 2016-12-21.
 */

public class ImageUtil {

    public static void HelloImage(Context context, String imgUrl, ImageView view) {
        Glide.with(context).load(imgUrl).into(view);

    }
}
