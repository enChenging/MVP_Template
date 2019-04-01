package com.release.mvp.ui.page.recommendPage;

import android.view.View;
import android.widget.ListView;

import com.release.mvp.R;
import com.release.mvp.presenter.page.recommendPage.RecommendPagePresenter;
import com.release.mvp.presenter.page.recommendPage.RecommendPageView;
import com.release.mvp.ui.adapter.RecommendAdapter;
import com.release.mvp.ui.base.BaseFragment;
import com.release.mvp.widget.CustomEmptyView;

import butterknife.BindView;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class RecommendPage extends BaseFragment implements RecommendPageView {
    private static final String TAG = RecommendPage.class.getSimpleName();

    @BindView(R.id.listView)
    ListView mListView;
    @BindView(R.id.empty_layout)
    CustomEmptyView mEmptyLayout;


    private RecommendAdapter mAdapter;
    private RecommendPagePresenter mPresenter;

    public static RecommendPage newInstance() {
        return new RecommendPage();
    }

    @Override
    public int getLayoutId() {
        return R.layout.page_recomment;
    }

    @Override
    public void initView(View view) {
        mPresenter = new RecommendPagePresenter(this);

        mAdapter = new RecommendAdapter(getContext(), mPresenter.list);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

        mPresenter.loadData(this);
    }

    @Override
    public void showEmptyView() {
        mEmptyLayout.setVisibility(View.VISIBLE);
        mEmptyLayout.setEmptyImage(R.mipmap.img_tips_error_load_error);
        mEmptyLayout.setEmptyText("加载失败~(≧▽≦)~啦啦啦.");
    }

    @Override
    public void hideEmptyView() {
        mEmptyLayout.setVisibility(View.GONE);
    }

    @Override
    public void finishTask() {
        hideEmptyView();
        mAdapter.notifyDataSetChanged();
    }
}
