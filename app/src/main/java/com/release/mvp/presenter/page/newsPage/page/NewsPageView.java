package com.release.mvp.presenter.page.newsPage.page;

import com.release.mvp.dao.NewsTypeInfo;

import java.util.List;

/**
 * @author Mr.release
 * @create 2019/4/16
 * @Describe
 */
public interface NewsPageView {

    void loadData(List<NewsTypeInfo> checkList);
}
