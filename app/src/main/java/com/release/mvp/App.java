package com.release.mvp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.release.mvp.dao.DaoSession;
import com.release.mvp.dao.NewsTypeDao;
import com.release.mvp.utils.CrashHandler;
import com.release.mvp.utils.SPUtil;

import javax.inject.Inject;

import androidx.multidex.MultiDex;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class App extends Application implements HasActivityInjector {

    public static App mContext;

    @Inject
    DaoSession mDaoSession;
    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    public static App getInstance() {
        return mContext;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().create(this).inject(this);
        mContext = this;
        init();
    }

    private void init() {
        SPUtil.getInstance(this);
        CrashHandler.getInstance().init(getApplicationContext());
        NewsTypeDao.updateLocalData(this, mDaoSession);
    }

    /**
     * token过期
     */
    public void tokenExpire() {
    }
}
