package com.release.mvp.ui.page.newsPage;

import android.view.View;
import android.widget.ListView;

import com.release.mvp.R;
import com.release.mvp.presenter.page.newsPage.improtNews.ImportantNewsPersenter;
import com.release.mvp.presenter.page.newsPage.improtNews.ImportantNewsView;
import com.release.mvp.ui.adapter.ImportantNewsAdapter;
import com.release.mvp.ui.base.BaseFragment;
import com.release.mvp.widget.CustomEmptyView;

import butterknife.BindView;

/**
 * 要闻
 *
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class ImportantNewsFragment extends BaseFragment implements ImportantNewsView {

    private static final String TAG = ImportantNewsFragment.class.getSimpleName();
    @BindView(R.id.lv_list_news)
    ListView mLvListNews;
    @BindView(R.id.empty_layout)
    CustomEmptyView mEmptyLayout;

    private ImportantNewsAdapter mAdapter;
    private ImportantNewsPersenter mPersenter;


    public static ImportantNewsFragment newInstance() {
        return new ImportantNewsFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_important_news;
    }

    @Override
    public void initView(View view) {
        mPersenter = new ImportantNewsPersenter(this);
        mAdapter = new ImportantNewsAdapter(getContext(), mPersenter.list);
        mLvListNews.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mPersenter.loadData(this);
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
