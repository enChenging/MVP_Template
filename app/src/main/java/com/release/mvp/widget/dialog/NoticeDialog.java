package com.release.mvp.widget.dialog;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.release.mvp.R;

/**
 * Created by Corleone on 2018/7/6.
 */
public class NoticeDialog extends Dialog {
    private static final String TAG = NoticeDialog.class.getSimpleName();

    private static NoticeDialog noticeDialog;

    private View.OnClickListener mListener;
    private TextView mTv_content, mTv_title;
    private boolean canNotCancel;
    private TextView mTv_cancel;
    private TextView mTv_ok;
    private final ImageView mIv_close;

    public NoticeDialog(final Context ctx, boolean canNotCancel, String title, String content,
                        View.OnClickListener listener, boolean isCanceledOnTouchOutside) {
        super(ctx, R.style.MyDialog);
        this.canNotCancel = canNotCancel;
        this.mListener = listener;

        setContentView(R.layout.dialog_welcom_permission);

        mTv_title = findViewById(R.id.tv_title);
        mTv_content = findViewById(R.id.tv_content);
        mTv_cancel = findViewById(R.id.tv_cancel);
        mTv_ok = findViewById(R.id.tv_ok);
        mIv_close = findViewById(R.id.iv_close);


        mTv_title.setText(title + "");
        mTv_content.setText(content + "");

        mTv_cancel.setOnClickListener(mListener);
        mTv_ok.setOnClickListener(mListener);
        mIv_close.setOnClickListener(mListener);

        WindowManager windowManager = ((Activity) ctx).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth() * 4 / 5;
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(isCanceledOnTouchOutside);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (canNotCancel) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public static void show(Context context, String content, View.OnClickListener listener) {
        show(context, "提醒", content, false, true, listener);
    }

    public static void show(Context context, String content, View.OnClickListener listener, boolean isCanceledOnTouchOutside) {
        show(context, "提醒", content, false, isCanceledOnTouchOutside, listener);
    }

    public static void show(Context context, String title, String content, View.OnClickListener listener) {
        show(context, title, content, false, true, listener);
    }


    public static void show(Context context, String title, String content, boolean isCanceledOnTouchOutside, View.OnClickListener listener) {
        show(context, title, content, false, isCanceledOnTouchOutside, listener);
    }


    public static void show(Context context, String title, String content, boolean isCancel, boolean isCanceledOnTouchOutside, View.OnClickListener listener) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        if (noticeDialog != null && noticeDialog.isShowing()) {
            return;
        }
        noticeDialog = new NoticeDialog(context, isCancel, title, content, listener, isCanceledOnTouchOutside);
        noticeDialog.show();
    }


    public static void dismissDialog() {
        if (noticeDialog != null && noticeDialog.isShowing() && !noticeDialog.getContext().isRestricted()) {
            noticeDialog.dismiss();
            noticeDialog = null;
        }
    }

    public static void setWindowAlpa(Activity context, boolean isopen) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        final Window window = ((Activity) context).getWindow();
        final WindowManager.LayoutParams lp = window.getAttributes();
        window.setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ValueAnimator animator;
        if (isopen) {
            animator = ValueAnimator.ofFloat(1.0f, 0.5f);
        } else {
            animator = ValueAnimator.ofFloat(0.5f, 1.0f);
        }
        animator.setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                lp.alpha = alpha;
                window.setAttributes(lp);
            }
        });
        animator.start();
    }

}

