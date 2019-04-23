package com.release.mvp.presenter.page.newsPage.detail;

import com.release.mvp.bean.NewsDetailInfoBean;
import com.release.mvp.presenter.base.BaseView;

/**
 * @author Mr.release
 * @create 2019/4/15
 * @Describe
 */
public interface NewsDetailView extends BaseView {

    void loadDataView(NewsDetailInfoBean newsDetailInfoBean);
}
