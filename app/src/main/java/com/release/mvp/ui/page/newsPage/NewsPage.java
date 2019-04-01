package com.release.mvp.ui.page.newsPage;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.release.mvp.R;
import com.release.mvp.ui.adapter.NewsAdapter;
import com.release.mvp.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class NewsPage extends BaseFragment {

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

    private String[] titles = {"要闻", "视频", "财经", "娱乐", "科技"};

    public static NewsPage newInstance() {
        return new NewsPage();
    }

    @Override
    public int getLayoutId() {
        return R.layout.page_news;
    }

    @Override
    public void initView(View view) {
        initEvent();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    private void initEvent() {
        NewsAdapter adapter = new NewsAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mStlTabLayout.setViewPager(mViewPager, titles);
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
}
