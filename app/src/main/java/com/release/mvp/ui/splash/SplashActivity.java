package com.release.mvp.ui.splash;

import android.Manifest;
import android.os.Build;
import android.view.View;
import android.widget.Button;

import com.release.mvp.R;
import com.release.mvp.presenter.splash.SplashPresenter;
import com.release.mvp.presenter.splash.SplashView;
import com.release.mvp.ui.base.BaseActivity;
import com.release.mvp.ui.guide.GuideActivity;
import com.release.mvp.ui.home.MainActivity;
import com.release.mvp.utils.StatusBarUtil;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;


/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
@RuntimePermissions
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {

    @BindView(R.id.btn_jump)
    Button mBtnJump;
    @BindView(R.id.btn_permission)
    Button mBtnPermission;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }


    @Override
    public void initView() {
    }

    @Override
    public void initListener() {

    }

    @Override
    public void updateViews(boolean isRefresh) {

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
                if (Build.VERSION.SDK_INT >= 23 && !hasPermission)
                    SplashActivityPermissionsDispatcher.needsPermissionWithPermissionCheck(this);
                else
                    goHome();
                break;
            case R.id.btn_permission:
                if (Build.VERSION.SDK_INT >= 23)
                    SplashActivityPermissionsDispatcher.needsPermissionWithPermissionCheck(this);
                break;
        }
    }

    private boolean isVisiable;

    @Override
    protected void onResume() {
        super.onResume();
        isVisiable = true;

        if (Build.VERSION.SDK_INT >= 23 && !isRequest) {
            isRequest = false;
            SplashActivityPermissionsDispatcher.needsPermissionWithPermissionCheck(this);
        } else if (Build.VERSION.SDK_INT < 23) {
            mPresenter.jump();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private boolean isBack;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isBack = true;
        mPresenter.exit(this);
    }

    @Override
    public void waitJump() {
        mBtnJump.setVisibility(View.VISIBLE);
        mBtnPermission.setVisibility(View.GONE);
        mPresenter.countdown(this, 6, mBtnJump, isBack, isVisiable);
    }


    //-----------------------------------------------Permission--------------------------------------------
    public boolean isRequest;
    private boolean isNever;
    private boolean hasPermission;
    private PermissionRequest mRequest;

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void needsPermission() {
        hasPermission = true;
        mPresenter.jump();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SplashActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onShowRationale(final PermissionRequest request) {
        mRequest = request;
        mPresenter.showNotice(this, getResources().getString(R.string.rationale_wr), mRequest, isNever);
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onPermissionDenied() {
        isRequest = true;
        mBtnPermission.setVisibility(View.VISIBLE);
    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onNeverAskAgain() {
        isRequest = true;
        isNever = true;
        mBtnPermission.setVisibility(View.VISIBLE);
        mPresenter.showNotice(this, getResources().getString(R.string.rationale_ask_again), mRequest, isNever);
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparent(this);
    }
}
