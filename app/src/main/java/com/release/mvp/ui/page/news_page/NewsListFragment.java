package com.release.mvp.ui.page.news_page;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.release.mvp.R;
import com.release.mvp.bean.NewsInfoBean;
import com.release.mvp.presenter.page.newsPage.news_list.NewsListPresenter;
import com.release.mvp.presenter.page.newsPage.news_list.NewsListView;
import com.release.mvp.ui.adapter.NewsListAdapter;
import com.release.mvp.ui.adapter.item.NewsMultiItem;
import com.release.mvp.ui.base.BaseFragment;
import com.release.mvp.utils.LogUtils;

import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import static com.release.mvp.ui.base.Constants.NEWS_TYPE_KEY;

/**
 * 要闻
 *
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class NewsListFragment extends BaseFragment<NewsListPresenter> implements NewsListView {

    private static final String TAG = NewsListFragment.class.getSimpleName();

    @BindView(R.id.rv_news_list)
    RecyclerView rvNewsList;
    @Inject
    NewsListAdapter mAdapter;


    public static NewsListFragment newInstance(String newsId) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NEWS_TYPE_KEY, newsId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news_list;
    }

    @Override
    public void initView(View view) {
        String newsId = getArguments().getString(NEWS_TYPE_KEY);
        mPresenter.setNewsId(newsId);

        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        rvNewsList.setHasFixedSize(true);//让RecyclerView避免重新计算大小
        rvNewsList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNewsList.setAdapter(mAdapter);

    }

    @Override
    public void initListener() {
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadMoreData();
            }
        }, rvNewsList);
    }

    @Override
    public void loadAdDataView(NewsInfoBean newsBean) {

    }

    @Override
    public void updateViews(boolean isRefresh) {
        mPresenter.loadData(isRefresh);
    }

    @Override
    public void loadDataView(List<NewsMultiItem> data) {
        LogUtils.i(TAG, "loadData: " + data.size());
        mAdapter.setNewData(data);
    }

    @Override
    public void loadMoreDataView(List<NewsMultiItem> data) {
        LogUtils.i(TAG, "loadMoreData: " + data.size());
        mAdapter.loadMoreComplete();
        mAdapter.addData(data);
    }

    @Override
    public void loadNoDataView() {
        mAdapter.loadMoreEnd();
    }
}
