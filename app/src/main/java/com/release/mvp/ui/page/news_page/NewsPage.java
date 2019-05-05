package com.release.mvp.ui.page.news_page;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.release.mvp.R;
import com.release.mvp.dao.NewsTypeInfo;
import com.release.mvp.presenter.page.newsPage.page.NewsPagePresenter;
import com.release.mvp.presenter.page.newsPage.page.NewsPageView;
import com.release.mvp.ui.base.BaseFragment;
import com.release.mvp.ui.base.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class NewsPage extends BaseFragment<NewsPagePresenter> implements NewsPageView {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.edit_text)
    EditText mEditText;
    @BindView(R.id.rl_top)
    RelativeLayout mRlTop;
    @BindView(R.id.stl_tab_layout)
    SlidingTabLayout mStlTabLayout;
    @Inject
    ViewPagerAdapter mAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.page_news;
    }

    @OnClick({R.id.iv_setting, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                mActivity.toggle();
                break;
            case R.id.iv_search:
                Toast.makeText(mActivity, "搜索", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void updateViews(boolean isRefresh) {
        mPresenter.loadData();
    }


    @Override
    public void loadDataView(List<NewsTypeInfo> list) {

        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();

        for (NewsTypeInfo bean : list) {
            titles.add(bean.getName());
            fragments.add(NewsListFragment.newInstance(bean.getTypeId()));
        }

        mAdapter.setItems(fragments, titles);
        mViewPager.setAdapter(mAdapter);
        mStlTabLayout.setViewPager(mViewPager);
    }
}
