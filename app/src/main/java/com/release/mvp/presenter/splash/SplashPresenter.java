package com.release.mvp.presenter.splash;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.release.mvp.R;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.ui.base.Constants;
import com.release.mvp.ui.splash.SplashActivity;
import com.release.mvp.utils.AppManager;
import com.release.mvp.utils.InstallUtil;
import com.release.mvp.utils.LogUtils;
import com.release.mvp.utils.RxHelper;
import com.release.mvp.utils.SPUtil;
import com.release.mvp.utils.baserx.CommonSubscriber;
import com.release.mvp.utils.baserx.RxUtil;
import com.release.mvp.widget.dialog.NoticeDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

/**
 * @author Mr.releasel
 * @create 2019/3/27
 * @Describe
 */
public class SplashPresenter extends BasePresenter<SplashView> {

    private static final String TAG = SplashPresenter.class.getSimpleName();

    @Inject
    protected SplashPresenter(SplashView view) {
        super(view);
    }

    public void jump() {
        Boolean loginFirst = (Boolean) SPUtil.getParam(Constants.LOGIN_FIRST, true);
        if (loginFirst) {
            view.goGuide();
            SPUtil.setParam(Constants.LOGIN_FIRST, false);
        } else {
            view.waitJump();
        }
    }

    @SuppressLint("CheckResult")
    public void countdown(SplashActivity activity, int time, Button mBtnJump) {
        RxHelper.countdown(time)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .doOnSubscribe((disposable) -> mBtnJump.setText("跳过(6)"))
                .as(RxUtil.bindLifecycle(activity))
                .subscribeWith(new CommonSubscriber<Long>() {
                    @Override
                    protected void _onNext(Long aLong) {
                        mBtnJump.setText("跳过(" + aLong + ")");
                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtils.e(TAG, "_onError: " + message);
                    }

                    @Override
                    protected void _onComplete() {
                        LogUtils.i(TAG, "_onComplete: ");
                        if (activity.isVisiable)
                            view.goHome();
                    }
                });
    }

    public void exit(Context context) {
        AppManager.appExit(context);
    }


    //-----------------------------------------------Permission--------------------------------------------
    private boolean isNever;
    public boolean hasPermission;
    private SplashActivity mActivity;

    @SuppressLint("CheckResult")
    public void requestCameraPermissions(SplashActivity activity) {
        mActivity = activity;
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEachCombined
                (Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> {
                    if (permission.granted) {
                        hasPermission = true;
                        jump();
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        showNotice(activity, activity.getResources().getString(R.string.rationale_wr), isNever);
                    } else {
                        isNever = true;
                        showNotice(activity, activity.getResources().getString(R.string.rationale_ask_again), isNever);
                    }
                });

    }

    public void showNotice(SplashActivity context, String content, boolean isNever) {
        NoticeDialog.show(context, content, (v -> noticeListener(context, v, isNever)));
    }

    public void noticeListener(SplashActivity context, View v, boolean isNever) {
        switch (v.getId()) {
            case R.id.iv_close:
            case R.id.tv_cancel:
                context.mBtnPermission.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_ok:
                if (isNever)
                    InstallUtil.startAppSettings(context);
                else
                    requestCameraPermissions(mActivity);
                break;
        }
        NoticeDialog.dismissDialog();
    }
}
