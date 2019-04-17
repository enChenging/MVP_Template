package com.release.mvp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.release.mvp.R;
import com.release.mvp.presenter.home.MainPersenter;
import com.release.mvp.presenter.home.MainView;
import com.release.mvp.ui.base.BaseActivity;
import com.release.mvp.utils.StatusBarUtil;
import com.release.mvp.widget.bottom_navigation.BottomNavigationViewEx;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;
import cn.jzvd.Jzvd;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class MainActivity extends BaseActivity implements MainView, NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.bottom_navigation)
    BottomNavigationViewEx mBottomNavigation;
    @BindView(R.id.left_navigation)
    NavigationView mLeftNavigation;
    @BindView(R.id.dl_drawer)
    DrawerLayout mDlDrawer;

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
        View headerView = mLeftNavigation.getHeaderView(0);
        mLeftNavigation.setItemIconTintList(null);
        ImageView headImg = headerView.findViewById(R.id.headImg);

        mPersenter = new MainPersenter(this);
        mPersenter.loadHeadView(this, headImg);

        mBottomNavigation.enableAnimation(false);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_nav);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(mBottomNavigation, navController);
    }

    @Override
    public void initListener() {

        mDlDrawer.setScrimColor(getResources().getColor(R.color.black_alpha_32));
        mDlDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (drawerView != null && drawerView.getTag().equals("left")) {
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

        mLeftNavigation.setNavigationItemSelectedListener(this);
    }

    @Override
    public void updateViews(boolean isRefresh) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_help_center:
                Toast.makeText(MainActivity.this, "帮助中心", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_setting:
                Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_camera:
                Toast.makeText(MainActivity.this, "照相机", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_gallery:
                Toast.makeText(MainActivity.this, "相册", Toast.LENGTH_SHORT).show();
                break;
        }
        toggle();
        return false;
    }


    @Override
    public void toggle() {
        mPersenter.toggle(mDlDrawer);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mPersenter.exit(this);
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

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setColorForDrawerLayout(this, mDlDrawer, getResources().getColor(R.color.colorPrimary), 0);
    }


    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.resetAllVideos();
    }

}
