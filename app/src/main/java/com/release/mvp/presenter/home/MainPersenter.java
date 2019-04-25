package com.release.mvp.presenter.home;

import android.widget.Toast;

import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.ui.home.MainActivity;
import com.release.mvp.utils.AppManager;

import javax.inject.Inject;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * @author Mr.release
 * @create 2019/3/28
 * @Describe
 */
public class MainPersenter extends BasePresenter<MainView> {


    private long mExitTime = 0;

    @Inject
    protected MainPersenter(MainView view) {
        super(view);
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

        if (!view.closeDrawableLayout()) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                Toast.makeText(context, "再按一次退出", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                AppManager.appExit(context);
            }
        }
    }
}
