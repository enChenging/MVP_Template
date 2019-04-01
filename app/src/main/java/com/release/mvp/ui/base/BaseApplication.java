package com.release.mvp.ui.base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.release.mvp.utils.CrashHandler;
import com.release.mvp.utils.SPUtil;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class BaseApplication extends MultiDexApplication {
    public static Context mContext;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        SPUtil.getInstance(this);
        CrashHandler.getInstance().init(getApplicationContext());
    }
}
