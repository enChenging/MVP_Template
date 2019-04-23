package com.release.mvp.presenter.page.recommendPage;

import com.release.mvp.presenter.base.BasePresenter;

import javax.inject.Inject;

/**
 * @author Mr.release
 * @create 2019/4/1
 * @Describe
 */
public class RecommendPagePresenter extends BasePresenter<RecommendPageView> {

    @Inject
    protected RecommendPagePresenter(RecommendPageView view) {
        super(view);
    }

    @Override
    public void loadData() {
        view.loadDataView(null);
    }

    @Override
    public void loadData(boolean isRefresh) {

    }

    @Override
    public void loadMoreData() {

    }
}
