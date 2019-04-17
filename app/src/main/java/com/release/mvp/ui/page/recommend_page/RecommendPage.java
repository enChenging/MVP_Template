package com.release.mvp.ui.page.recommend_page;

import android.view.View;

import com.release.mvp.R;
import com.release.mvp.bean.NewsInfoBean;
import com.release.mvp.presenter.page.recommendPage.RecommendPagePresenter;
import com.release.mvp.presenter.page.recommendPage.RecommendPageView;
import com.release.mvp.ui.adapter.RecommendAdapter;
import com.release.mvp.ui.base.BaseFragment;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class RecommendPage extends BaseFragment implements RecommendPageView {
    private static final String TAG = RecommendPage.class.getSimpleName();


    private RecommendAdapter mAdapter;
    private RecommendPagePresenter mPresenter;

    public static RecommendPage newInstance() {
        return new RecommendPage();
    }

    @Override
    public int getLayoutId() {
        return R.layout.page_recommend;
    }

    @Override
    public void initView(View view) {
        mPresenter = new RecommendPagePresenter(this);

        mAdapter = new RecommendAdapter(getContext(), mPresenter.list);
    }

    @Override
    public void initListener() {

    }


    @Override
    public void loadAdData(NewsInfoBean newsBean) {

    }

    @Override
    public void loadData(Object data) {

    }

    @Override
    public void loadMoreData(Object data) {

    }

    @Override
    public void loadNoData() {

    }

    @Override
    public void updateViews(boolean isRefresh) {

    }
}
