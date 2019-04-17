package com.release.mvp.presenter.page.recommendPage;

import com.release.mvp.bean.RecommendPageBean;
import com.release.mvp.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.release
 * @create 2019/4/1
 * @Describe
 */
public class RecommendPagePresenter implements BasePresenter {

    private RecommendPageView mView;
    public List<RecommendPageBean.DataBean> list = new ArrayList<>();

    public RecommendPagePresenter(RecommendPageView view) {
        this.mView = view;
    }


    @Override
    public void loadData(boolean isRefresh) {

    }

    @Override
    public void loadMoreData() {

    }
}
