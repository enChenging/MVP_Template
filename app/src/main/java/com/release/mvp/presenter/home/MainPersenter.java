package com.release.mvp.presenter.home;

import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.release.mvp.ui.home.MainActivity;
import com.release.mvp.utils.AppManager;
import com.release.mvp.utils.GlideApp;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author Mr.release
 * @create 2019/3/28
 * @Describe
 */
public class MainPersenter {


    private MainView mView;

    public MainPersenter(MainView view) {
        this.mView = view;
    }

    public void loadHeadView(Context context, ImageView view) {
        GlideApp.with(context).load("https://b-ssl.duitang.com/uploads/item/201802/20/20180220170028_JcYMU.jpeg").circleCrop().into(view);
    }

    public void toggle(DrawerLayout mDlDrawer) {
        int drawerLockMode = mDlDrawer.getDrawerLockMode(GravityCompat.START);
        if (mDlDrawer.isDrawerVisible(GravityCompat.START) && (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_OPEN)) {
            mDlDrawer.closeDrawer(GravityCompat.START);
        } else if (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
            mDlDrawer.openDrawer(GravityCompat.START);
        }
    }

    public void exit(MainActivity context, Boolean isExit) {
        if (!mView.closeDrawableLayout()) {
            if (!isExit) {
                context.isExit = true;
                Toast.makeText(context, "再按一次退出", Toast.LENGTH_SHORT).show();
                delayMessage(context);
            } else {
                AppManager.getAppManager().appExit(context);
            }
        }
    }

    private void delayMessage(MainActivity context) {
        Observable.timer(2, TimeUnit.SECONDS)
                .compose(context.<Long>bindToLifecycle())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        context.isExit = false;
                    }
                });
    }

    public void onDestroy() {
        mView = null;
    }
}
