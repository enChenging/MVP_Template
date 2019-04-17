package com.release.mvp.dao;

import android.content.Context;

import com.alibaba.fastjson.JSONArray;
import com.release.mvp.utils.CommonUtil;

import java.util.List;

/**
 * @author Mr.release
 * @create 2019/3/29
 * @Describe
 */
public class NewsTypeDao {

    // 所有栏目
    private static List<NewsTypeInfo> sAllChannels;


    private NewsTypeDao() {
    }

    /**
     * 更新本地数据，如果数据库新闻列表栏目为 0 则添加头 3 个栏目
     *
     * @param context
     * @param daoSession
     */
    public static void updateLocalData(Context context, DaoSession daoSession) {
        sAllChannels = JSONArray.parseArray(CommonUtil.readData(context, "NewsChannel"), NewsTypeInfo.class);
        NewsTypeInfoDao beanDao = daoSession.getNewsTypeInfoDao();
        if (beanDao.count() == 0) {
//            beanDao.insertInTx(sAllChannels.subList(0, 3));
            beanDao.insertInTx(sAllChannels);
        }
    }

    /**
     * 获取所有栏目
     *
     * @return
     */
    public static List<NewsTypeInfo> getAllChannels() {
        return sAllChannels;
    }
}
