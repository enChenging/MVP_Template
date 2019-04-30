package com.release.mvp.presenter.page.newsPage.page;

import com.alibaba.fastjson.JSON;
import com.release.mvp.dao.DaoSession;
import com.release.mvp.dao.NewsTypeInfo;
import com.release.mvp.dao.NewsTypeInfoDao;
import com.release.mvp.injector.base.FragmentScope;
import com.release.mvp.injector.util.ActivityScopeUtil;
import com.release.mvp.injector.util.FragmentScopeUtil;
import com.release.mvp.injector.util.SingletonUtil;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.utils.LogUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Mr.release
 * @create 2019/4/16
 * @Describe
 */
@FragmentScope
public class NewsPagePresenter extends BasePresenter<NewsPageView> {

    private static final String TAG = NewsPagePresenter.class.getSimpleName();
    private final SingletonUtil singletonUtil;
    private final ActivityScopeUtil activityScopeUtil;
    private final FragmentScopeUtil fragmentScopeUtil;

    @Inject
    DaoSession mDaoSession;

    @Inject
    public NewsPagePresenter(NewsPageView view,
                             SingletonUtil singletonUtil,
                             ActivityScopeUtil activityScopeUtil,
                             FragmentScopeUtil fragmentScopeUtil) {
        super(view);
        this.singletonUtil = singletonUtil;
        this.activityScopeUtil = activityScopeUtil;
        this.fragmentScopeUtil = fragmentScopeUtil;
    }

    @Override
    public void loadData() {

        NewsTypeInfoDao newsTypeInfoDao = mDaoSession.getNewsTypeInfoDao();
        List<NewsTypeInfo> newsTypeInfos = newsTypeInfoDao.loadAll();

        String s = JSON.toJSONString(newsTypeInfos);
        LogUtils.i(TAG, "loadData: " + newsTypeInfos.size());
        LogUtils.i(TAG, "s: " + s);
        view.loadDataView(newsTypeInfos);
    }


    public void onDoSomething() {
        // Do something here. Maybe make an asynchronous call to fetch data...
        String something = singletonUtil.doSomething();
        something += "\n" + activityScopeUtil.doSomething();
        something += "\n" + fragmentScopeUtil.doSomething();
        LogUtils.i(TAG, "onDoSomething: " + something);
    }
}
