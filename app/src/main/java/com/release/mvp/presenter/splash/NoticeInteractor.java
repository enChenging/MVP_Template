package com.release.mvp.presenter.splash;

import android.view.View;

import com.release.mvp.R;
import com.release.mvp.ui.splash.SplashActivity;
import com.release.mvp.utils.InstallUtil;
import com.release.mvp.widget.dialog.NoticeDialog;

import permissions.dispatcher.PermissionRequest;

/**
 * @author Mr.release
 * @create 2019/4/1
 * @Describe
 */
public class NoticeInteractor {


    public void noticeListener(SplashActivity context, View v, PermissionRequest mRequest, boolean isNever) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                if (mRequest != null && !isNever)
                    mRequest.cancel();
                break;
            case R.id.tv_ok:
                if (mRequest != null && !isNever)
                    mRequest.proceed();
                if (isNever) {
                    context.isRequest = false;
                    InstallUtil.startAppSettings(context);
                }
                break;
        }
        NoticeDialog.dismissDialog();
    }
}
