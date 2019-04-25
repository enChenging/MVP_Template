package com.release.mvp.ui.page.recommend_page;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.release.mvp.R;
import com.release.mvp.bean.RecommendPageBean;
import com.release.mvp.presenter.page.recommendPage.RecommendPagePresenter;
import com.release.mvp.presenter.page.recommendPage.RecommendPageView;
import com.release.mvp.ui.adapter.RecommendAdapter;
import com.release.mvp.ui.base.BaseFragment;
import com.release.mvp.ui.web_detail.WebDetailActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class RecommendPage extends BaseFragment<RecommendPagePresenter> implements RecommendPageView {
    private static final String TAG = RecommendPage.class.getSimpleName();


    @BindView(R.id.rv_list)
    RecyclerView mRvList;
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

        mRvList.setHasFixedSize(true);
        mRvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvList.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                RecommendPageBean.NewslistBean bean = (RecommendPageBean.NewslistBean) adapter.getData().get(position);
                WebDetailActivity.start(mContext, bean.getTitle(), bean.getCtime(), bean.getDescription(), bean.getUrl());
            }
        });
    }

    @Override
    public void updateViews(boolean isRefresh) {
        mPresenter.loadData();
    }

    @Override
    public void loadDataView(List<RecommendPageBean.NewslistBean> data) {
        mAdapter.setNewData(data);
    }

    @Override
    public void loadMoreDataView(List<RecommendPageBean.NewslistBean> data) {

    }

    @Override
    public void loadNoDataView() {

    }


}
