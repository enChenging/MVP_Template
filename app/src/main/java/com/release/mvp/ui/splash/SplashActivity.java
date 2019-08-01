package com.release.mvp.ui.splash;

import android.os.Build;
import android.view.View;
import android.widget.Button;

import com.release.mvp.R;
import com.release.mvp.presenter.splash.SplashPresenter;
import com.release.mvp.presenter.splash.SplashView;
import com.release.mvp.ui.base.BaseActivity;
import com.release.mvp.ui.guide.GuideActivity;
import com.release.mvp.MainActivity;
import com.release.mvp.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {

    @BindView(R.id.btn_jump)
    Button mBtnJump;
    @BindView(R.id.btn_permission)
    public Button mBtnPermission;

    public boolean isVisiable;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }


    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= 23)
            mPresenter.requestCameraPermissions(this);
        else
            mPresenter.jump();
    }

    @Override
    public void goGuide() {
        GuideActivity.start(this);
    }

    @Override
    public void goHome() {
        MainActivity.start(this);
    }

    @OnClick({R.id.btn_jump, R.id.btn_permission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_jump:
                if (Build.VERSION.SDK_INT >= 23 && !mPresenter.hasPermission)
                    mPresenter.requestCameraPermissions(this);
                else
                    goHome();
                break;
            case R.id.btn_permission:
                if (Build.VERSION.SDK_INT >= 23)
                    mPresenter.requestCameraPermissions(this);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isVisiable = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mPresenter.exit(this);
    }

    @Override
    public void waitJump() {
        mBtnJump.setVisibility(View.VISIBLE);
        mBtnPermission.setVisibility(View.GONE);
        mPresenter.countdown(this, 6, mBtnJump);
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparent(this);
    }
}
