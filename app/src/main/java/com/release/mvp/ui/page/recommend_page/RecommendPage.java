package com.release.mvp.ui.page.recommend_page;

import android.view.View;

import com.release.mvp.R;
import com.release.mvp.bean.RecommendPageBean;
import com.release.mvp.presenter.page.recommendPage.RecommendPagePresenter;
import com.release.mvp.presenter.page.recommendPage.RecommendPageView;
import com.release.mvp.ui.adapter.RecommendAdapter;
import com.release.mvp.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class RecommendPage extends BaseFragment<RecommendPagePresenter> implements RecommendPageView {
    private static final String TAG = RecommendPage.class.getSimpleName();


    @Inject
    RecommendAdapter mAdapter;

    public static RecommendPage newInstance() {
        return new RecommendPage();
    }

    @Override
    public int getLayoutId() {
        return R.layout.page_recommend;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initListener() {

    }


    @Override
    public void loadDataView(List<RecommendPageBean.DataBean> data) {
        mAdapter.addData(data);
    }

    @Override
    public void loadMoreDataView(List<RecommendPageBean.DataBean> data) {

    }

    @Override
    public void loadNoDataView() {

    }

    @Override
    public void updateViews(boolean isRefresh) {
        mPresenter.loadData();
    }
}
