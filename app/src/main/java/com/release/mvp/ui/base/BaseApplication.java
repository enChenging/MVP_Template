package com.release.mvp.ui.base;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.release.mvp.dao.DaoMaster;
import com.release.mvp.dao.DaoSession;
import com.release.mvp.dao.NewsTypeDao;
import com.release.mvp.dao.NewsTypeInfoDao;
import com.release.mvp.utils.CrashHandler;
import com.release.mvp.utils.SPUtil;

import org.greenrobot.greendao.database.Database;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class BaseApplication extends MultiDexApplication {

    public static Context mContext;

    private static BaseApplication context;

    public static BaseApplication getInstance() {
        return context;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mContext = getApplicationContext();
        FragmentActivity applicationContext = (FragmentActivity) getApplicationContext();
        SPUtil.getInstance(this);
        CrashHandler.getInstance().init(getApplicationContext());
        initDao();
    }

    private static final String DB_NAME = "dao_db";
    private void initDao() {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME);
        Database database = helper.getWritableDb();
        daoSession = new DaoMaster(database).newSession();
        NewsTypeDao.updateLocalData(this, daoSession);
    }

    private DaoSession daoSession;

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
