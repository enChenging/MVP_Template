package com.release.mvp.utils;

import android.widget.Toast;

import com.release.mvp.ui.base.BaseApplication;

/**
 * Created by long on 2016/6/6.
 * 避免同样的信息多次触发重复弹出的问题
 */
public class ToastUtils {

    private static String oldMsg;
    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;

    private ToastUtils() {
        throw new RuntimeException("ToastUtils cannot be initialized!");
    }

    public static void showToast(String s) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.mContext, s, Toast.LENGTH_SHORT);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
            oneTime = twoTime;
        }
    }

    public static void showToast(int resId) {
        showToast(BaseApplication.mContext.getString(resId));
    }
}
