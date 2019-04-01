package com.release.mvp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.release.mvp.R;
import com.release.mvp.ui.base.Constants;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class LoadingStateView extends FrameLayout {

    private ImageView iv_empty;
    private LinearLayout ll_error;
    private ProgressBar pb_loading;

    private int state = Constants.STATE_LOADING;
    private RelativeLayout rl_load;
    private FrameLayout mFl_content;

    public LoadingStateView(Context context) {
        this(context, null);
    }

    public LoadingStateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rl_load = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.view_loading_state, null);
        iv_empty = (ImageView) rl_load.findViewById(R.id.view_empty);
        ll_error = (LinearLayout) rl_load.findViewById(R.id.view_error);
        pb_loading = (ProgressBar) rl_load.findViewById(R.id.view_pb_loading);
        mFl_content = (FrameLayout) rl_load.findViewById(R.id.realtabcontent);
        updateView(state);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(rl_load, params);
    }


    /**
     * 根据不同的状态值，更新当前的View
     * @param currentState
     */
    public void updateView(int currentState) {
        switch (currentState) {
            case Constants.STATE_SUCCESS:
                mFl_content.setVisibility(View.VISIBLE);
                iv_empty.setVisibility(View.INVISIBLE);
                ll_error.setVisibility(View.INVISIBLE);
                pb_loading.setVisibility(View.INVISIBLE);
                break;
            case Constants.STATE_ERROR:
                mFl_content.setVisibility(View.INVISIBLE);
                iv_empty.setVisibility(View.INVISIBLE);
                ll_error.setVisibility(View.VISIBLE);
                pb_loading.setVisibility(View.INVISIBLE);
                break;
            case Constants.STATE_LOADING:
                mFl_content.setVisibility(View.INVISIBLE);
                iv_empty.setVisibility(View.INVISIBLE);
                ll_error.setVisibility(View.INVISIBLE);
                pb_loading.setVisibility(View.VISIBLE);
                break;
            case Constants.STATE_EMPTY:
                mFl_content.setVisibility(View.INVISIBLE);
                iv_empty.setVisibility(View.VISIBLE);
                ll_error.setVisibility(View.INVISIBLE);
                pb_loading.setVisibility(View.INVISIBLE);
                break;
        }
    }

}
