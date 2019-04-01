package com.release.mvp.utils;

import android.content.Context;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class DensityUtil {

    public static int dip2px(Context context,float dip){
        //px/dip= density
        float density = context.getResources().getDisplayMetrics().density;

        return (int) (dip*density+0.5f);
    }

    public static int px2dip(Context context,float px){
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px/density+0.5f);
    }
}
