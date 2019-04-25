package com.release.mvp.presenter.splash;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Button;

import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.ui.base.Constants;
import com.release.mvp.ui.splash.SplashActivity;
import com.release.mvp.utils.AppManager;
import com.release.mvp.utils.LogUtils;
import com.release.mvp.utils.RxHelper;
import com.release.mvp.utils.SPUtil;
import com.release.mvp.utils.baserx.CommonSubscriber;
import com.release.mvp.utils.baserx.RxUtil;
import com.release.mvp.widget.dialog.NoticeDialog;

import javax.inject.Inject;

import permissions.dispatcher.PermissionRequest;

/**
 * @author Mr.releasel
 * @create 2019/3/27
 * @Describe
 */
public class SplashPresenter extends BasePresenter<SplashView> {

    private static final String TAG = SplashPresenter.class.getSimpleName();
    private NoticeInteractor noticeInteractor;

    @Inject
    protected SplashPresenter(SplashView view) {
        super(view);
        noticeInteractor = new NoticeInteractor();
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
    public void countdown(SplashActivity activity, int time, Button mBtnJump, boolean isBack, boolean isVisiable) {
        RxHelper.countdown(time)
                .compose(activity.<Long>bindToLifecycle())
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .doOnSubscribe((disposable) -> mBtnJump.setText("跳过(6)"))
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
                        if (!isBack && isVisiable)
                            view.goHome();
                    }
                });
    }

    public void exit(Context context) {
        AppManager.appExit(context);
    }


    public void showNotice(SplashActivity context, String content, PermissionRequest mRequest, boolean isNever) {
        NoticeDialog.show(context, content, (v -> noticeInteractor.noticeListener(context, v, mRequest, isNever)));
    }
}
