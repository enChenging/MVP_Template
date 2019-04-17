package com.release.mvp.presenter.page.newsPage.page;

import com.alibaba.fastjson.JSON;
import com.release.mvp.dao.DaoSession;
import com.release.mvp.dao.NewsTypeInfo;
import com.release.mvp.dao.NewsTypeInfoDao;
import com.release.mvp.presenter.BasePresenter;
import com.release.mvp.ui.base.BaseApplication;
import com.release.mvp.utils.LogUtils;

import java.util.List;

/**
 * @author Mr.release
 * @create 2019/4/16
 * @Describe
 */
public class NewsPagePresenter implements BasePresenter {
    private static final String TAG = NewsPagePresenter.class.getSimpleName();
    private final NewsPageView mView;

    public NewsPagePresenter(NewsPageView view) {
        mView = view;
    }

    @Override
    public void loadData(boolean isRefresh) {

        DaoSession daoSession = BaseApplication.getInstance().getDaoSession();
        NewsTypeInfoDao newsTypeInfoDao = daoSession.getNewsTypeInfoDao();
        List<NewsTypeInfo> newsTypeInfos = newsTypeInfoDao.loadAll();

        String s = JSON.toJSONString(newsTypeInfos);
        LogUtils.i(TAG, "loadData: " + newsTypeInfos.size());
        LogUtils.i(TAG, "s: " + s);
        mView.loadData(newsTypeInfos);
    }

    @Override
    public void loadMoreData() {

    }
}
