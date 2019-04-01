package com.release.mvp.ui.guide;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.release.mvp.R;
import com.release.mvp.presenter.guide.GuidePersenter;
import com.release.mvp.presenter.guide.GuideView;
import com.release.mvp.ui.adapter.GuideViewPagerAdapter;
import com.release.mvp.ui.base.BaseActivity;
import com.release.mvp.ui.home.MainActivity;
import com.release.mvp.utils.LogUtils;
import com.release.mvp.widget.pageTransformer.CubeOutTransformer;

import butterknife.BindView;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class GuideActivity extends BaseActivity implements GuideView {


    @BindView(R.id.view_viewpager)
    ViewPager mViewViewpager;
    @BindView(R.id.bt_home)
    Button mBtHome;
    @BindView(R.id.dot_group)
    LinearLayout mDotGroup;
    @BindView(R.id.dot_focus)
    View mDotFocus;


    private int dot_width;
    private GuidePersenter mPersenter;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, GuideActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        mPersenter = new GuidePersenter(this);
    }

    @Override
    public void initListener() {
        mBtHome.setOnClickListener((v -> goHome()));

        mViewViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LogUtils.i("positionOffsetPixels", "----------------" + positionOffsetPixels);
                int translate = (int) (dot_width * (position + positionOffset));
                mDotFocus.setTranslationX(translate);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == mPersenter.imageList.size() - 1) {
                    mBtHome.setVisibility(View.VISIBLE);
                } else {
                    mBtHome.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mDotFocus.postDelayed(() -> dot_width = mDotGroup.getChildAt(1).getLeft() - mDotGroup.getChildAt(0).getLeft(), 5);
    }

    @Override
    public void initData() {

        mPersenter.imageViews(this, mDotGroup);
        mViewViewpager.setAdapter(new GuideViewPagerAdapter(mPersenter.imageList));
        //效果
//        viewpager.setPageTransformer(true, new ZoomOutPageTransformer());
//        viewpager.setPageTransformer(true, new DepthPageTransformer());
//        viewpager.setPageTransformer(true, new RotatePageTransformer());
        mViewViewpager.setPageTransformer(true, new CubeOutTransformer());


    }

    @Override
    public void goHome() {
        MainActivity.start(this);
        finish();
    }

    @Override
    protected void onDestroy() {
        mPersenter.onDestroy();
        super.onDestroy();
    }
}
