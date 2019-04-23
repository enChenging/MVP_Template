package com.release.mvp.ui.page.video_page;

import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.release.mvp.R;
import com.release.mvp.presenter.page.videoPage.page.VideoPagePresenter;
import com.release.mvp.presenter.page.videoPage.page.VideoPageView;
import com.release.mvp.ui.base.BaseFragment;
import com.release.mvp.ui.base.ViewPagerAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class VideoPage extends BaseFragment<VideoPagePresenter> implements VideoPageView {


    @BindView(R.id.stl_tab_layout)
    SlidingTabLayout mStlTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @Inject
    ViewPagerAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.page_video;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void updateViews(boolean isRefresh) {
        mPresenter.loadData();
    }

    @Override
    public void loadDataView(ArrayList<Fragment> fragments, String[] strings) {
        mAdapter.setItems(fragments, strings);
        mViewPager.setAdapter(mAdapter);
        mStlTabLayout.setViewPager(mViewPager);
    }
}
