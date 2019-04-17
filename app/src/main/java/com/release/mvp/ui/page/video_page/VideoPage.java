package com.release.mvp.ui.page.video_page;

import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.release.mvp.R;
import com.release.mvp.ui.base.BaseFragment;
import com.release.mvp.ui.base.ViewPagerAdapter;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class VideoPage extends BaseFragment {


    @BindView(R.id.stl_tab_layout)
    SlidingTabLayout mStlTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private final String[] VIDEO_ID = new String[]{
            "V9LG4B3A0", "V9LG4E6VR", "V9LG4CHOR", "00850FRB"
    };
    private final String[] VIDEO_TITLE = new String[]{
            "热点", "搞笑", "娱乐", "精品"
    };

    @Override
    public int getLayoutId() {
        return R.layout.page_video;
    }

    @Override
    public void initView(View view) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < VIDEO_ID.length; i++) {
            fragments.add(VideoListFragment.newInstance(VIDEO_ID[i]));
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(mActivity.getSupportFragmentManager());
        adapter.setItems(fragments, VIDEO_TITLE);
        mViewPager.setAdapter(adapter);
        mStlTabLayout.setViewPager(mViewPager);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void updateViews(boolean isRefresh) {

    }
}
