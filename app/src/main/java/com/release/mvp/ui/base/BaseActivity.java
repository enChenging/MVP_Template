package com.release.mvp.ui.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.release.mvp.R;
import com.release.mvp.ui.guide.GuideActivity;
import com.release.mvp.ui.splash.SplashActivity;
import com.release.mvp.utils.StatusBarUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public abstract class BaseActivity extends RxAppCompatActivity implements View.OnClickListener, UIIterfaceAct {

    protected static String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG = this.getClass().getSimpleName();

        setContentView(getLayoutId());

        initTheme();

        ButterKnife.bind(this);

        initView();

        initCommonView();

        initListener();

        initData();

    }

    protected void initTheme() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setTranslucentStatus(this);
        if (this instanceof SplashActivity) {
            StatusBarUtil.setRootViewFitsSystemWindows(this, true);
            StatusBarUtil.setStatusBarDarkTheme(this, false);
        } else if (this instanceof GuideActivity) {
            StatusBarUtil.setRootViewFitsSystemWindows(this, false);
            StatusBarUtil.setStatusBarDarkTheme(this, false);
        } else {
            StatusBarUtil.setRootViewFitsSystemWindows(this, true);
            StatusBarUtil.setStatusBarDarkTheme(this, true);
        }
    }


    protected void initCommonView() {
        View back = findViewById(R.id.back);
        if (back != null) {
            back.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }


}
