package com.release.mvp.presenter.splash;

import android.content.Context;
import android.widget.Button;

import com.release.mvp.ui.base.Constants;
import com.release.mvp.ui.splash.SplashActivity;
import com.release.mvp.utils.AppManager;
import com.release.mvp.utils.RxHelper;
import com.release.mvp.utils.SPUtil;
import com.release.mvp.widget.dialog.NoticeDialog;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import permissions.dispatcher.PermissionRequest;

/**
 * @author Mr.releasel
 * @create 2019/3/27
 * @Describe
 */
public class SplashPresenter {

    private SplashView view;
    private NoticeInteractor noticeInteractor;

    public SplashPresenter(SplashView view,NoticeInteractor noticeInteractor) {
        this.view = view;
        this.noticeInteractor = noticeInteractor;
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

    public void countdown(SplashActivity activity, int time, Button mBtnJump, boolean isBack, boolean isVisiable) {
        RxHelper.countdown(time)
                .compose(activity.<Long>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe((disposable) -> mBtnJump.setText("跳过(6)"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        mBtnJump.setText("跳过(" + aLong + ")");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        if (!isBack && isVisiable) {
                            view.goHome();
                        }
                    }
                });
    }

    public void exit(Context context) {
        AppManager.getAppManager().appExit(context);
    }


    public void showNotice(SplashActivity context,String content,PermissionRequest mRequest,boolean isNever){
        NoticeDialog.show(context, content, (v -> noticeInteractor.noticeListener(context, v, mRequest, isNever)));
    }

    public void onDestroy() {
        view = null;
    }
}
