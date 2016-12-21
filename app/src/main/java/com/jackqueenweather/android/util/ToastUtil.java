package com.jackqueenweather.android.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016-12-21.
 */
public class ToastUtil {

    public static void showToast(final Activity activity, final String string) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity,string, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
