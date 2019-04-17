package com.release.mvp.presenter.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.release.mvp.ui.home.MainActivity;
import com.release.mvp.utils.AppManager;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * @author Mr.release
 * @create 2019/3/28
 * @Describe
 */
public class MainPersenter {


    private MainView mView;
    private long mExitTime = 0;

    public MainPersenter(MainView view) {
        this.mView = view;
    }

    public void loadHeadView(Context context, ImageView view) {
        Glide.with(context).load("https://b-ssl.duitang.com/uploads/item/201802/20/20180220170028_JcYMU.jpeg").circleCrop().into(view);
    }

    public void toggle(DrawerLayout mDlDrawer) {
        int drawerLockMode = mDlDrawer.getDrawerLockMode(GravityCompat.START);
        if (mDlDrawer.isDrawerVisible(GravityCompat.START) && (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_OPEN)) {
            mDlDrawer.closeDrawer(GravityCompat.START);
        } else if (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
            mDlDrawer.openDrawer(GravityCompat.START);
        }
    }

    public void exit(MainActivity context) {

        if (!mView.closeDrawableLayout()) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                Toast.makeText(context, "再按一次退出", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                AppManager.getAppManager().appExit(context);
            }
        }
    }

    public void onDestroy() {
        mView = null;
    }
}
