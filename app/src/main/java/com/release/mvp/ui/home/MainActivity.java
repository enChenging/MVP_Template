package com.release.mvp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.release.mvp.R;
import com.release.mvp.presenter.home.MainPersenter;
import com.release.mvp.presenter.home.MainView;
import com.release.mvp.ui.adapter.HomePagesAdapder;
import com.release.mvp.ui.base.BaseActivity;
import com.release.mvp.widget.LazyViewPager;
import com.release.mvp.widget.NoScrollViewPager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class MainActivity extends BaseActivity implements MainView {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.view_pager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.rb_news)
    RadioButton mRbNews;
    @BindView(R.id.rb_recommend)
    RadioButton mRbRecommend;
    @BindView(R.id.rb_live)
    RadioButton mRbLive;
    @BindView(R.id.user_photo_bg)
    ImageView mUserPhotoBg;
    @BindView(R.id.headImg)
    ImageView mHeadImg;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_user_duties)
    TextView mTvUserDuties;
    @BindView(R.id.id_draw_menu_header)
    RelativeLayout mIdDrawMenuHeader;
    @BindView(R.id.tv_help)
    TextView mTvHelp;
    @BindView(R.id.ll_help)
    LinearLayout mLlHelp;
    @BindView(R.id.tv_setting)
    TextView mTvSetting;
    @BindView(R.id.ll_setting)
    LinearLayout mLlSetting;
    @BindView(R.id.dl_drawer)
    DrawerLayout mDlDrawer;

    public Boolean isExit = false;
    private MainPersenter mPersenter;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mPersenter = new MainPersenter(this);
        mPersenter.loadHeadView(this, mHeadImg);
    }

    @Override
    public void initData() {
        mViewPager.setAdapter(new HomePagesAdapder(getSupportFragmentManager()));
        mViewPager.setCurrentItem(0);
        mRbNews.setChecked(true);
    }

    @Override
    public void initListener() {

        mDlDrawer.setScrimColor(getResources().getColor(R.color.black_alpha_32));
        mDlDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (drawerView.getTag().equals("left")) {
                    View content = mDlDrawer.getChildAt(0);
                    int offset = (int) (drawerView.getWidth() * slideOffset);
                    content.setTranslationX(offset);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        mViewPager.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mDlDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                        break;
                    case 1:
                    case 2:
                    case 3:
                        mDlDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.rb_news, R.id.rb_recommend, R.id.rb_live, R.id.id_draw_menu_header, R.id.ll_help, R.id.ll_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_news:
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.rb_recommend:
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.rb_live:
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.id_draw_menu_header:
                Toast.makeText(this, "点击头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_help:
                Toast.makeText(this, "帮助中心", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_setting:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void toggle() {
        mPersenter.toggle(mDlDrawer);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mPersenter.exit(this, isExit);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean closeDrawableLayout() {
        if (mDlDrawer.isDrawerVisible(GravityCompat.START)) {
            mDlDrawer.closeDrawer(GravityCompat.START);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        mPersenter.onDestroy();
        super.onDestroy();
    }
}
