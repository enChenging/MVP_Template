package com.release.mvp.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.release.mvp.R;
import com.release.mvp.dagger.base.ChildFragmentManager;
import com.release.mvp.presenter.base.BaseView;
import com.release.mvp.presenter.base.Presenter;
import com.release.mvp.ui.home.MainActivity;
import com.release.mvp.utils.SwipeRefreshHelper;
import com.release.mvp.widget.EmptyLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public abstract class BaseFragment<T extends Presenter> extends Fragment implements HasSupportFragmentInjector,
        UIIterfaceFrag, BaseView, EmptyLayout.OnRetryListener {

    public MainActivity mActivity;
    private Unbinder mUnbinder;

    @Nullable
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    @Nullable
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @Nullable
    @BindView(R.id.back)
    ImageView mBack;

    private View mRootView;

    @Inject
    protected Context mContext;

    @Inject
    @ChildFragmentManager
    protected FragmentManager childFragmentManager;

    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    @Inject
    protected T mPresenter;


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return childFragmentInjector;
    }


    @Override
    public void onAttach(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // Perform injection here for versions before M as onAttach(*Context*) did not yet exist
            // This fixes DaggerFragment issue: https://github.com/google/dagger/issues/777
            AndroidSupportInjection.inject(this);
        }
        super.onAttach(activity);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Perform injection here for M (API 23) due to deprecation of onAttach(*Activity*).
            AndroidSupportInjection.inject(this);
        }
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof MainActivity)
            mActivity = (MainActivity) getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUnbinder = ButterKnife.bind(this, view);
        initView(view);
        initListener();
        initSwipeRefresh();
        updateViews(false);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initListener() {
    }

    @Override
    public void updateViews(boolean isRefresh) {

    }

    @Override
    public void onDestroyView() {
        try {
            if (mUnbinder != null)
                mUnbinder.unbind();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        super.onDestroyView();
    }

    private void initCommonView() {
        if (mBack != null) {
            mBack.setOnClickListener((v -> getActivity().finish()));
        }
    }


    @Override
    public void showLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
            SwipeRefreshHelper.enableRefresh(mSwipeRefresh, false);
        }
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hide();
            SwipeRefreshHelper.enableRefresh(mSwipeRefresh, true);
            SwipeRefreshHelper.controlRefresh(mSwipeRefresh, false);
        }
    }

    @Override
    public void showNetError() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(this);
        }
    }


    @Override
    public void onRetry() {
        updateViews(false);
    }


    @Override
    public void finishRefresh() {
        if (mSwipeRefresh != null) {
            mSwipeRefresh.setRefreshing(false);
        }
    }


    /**
     * 初始化下拉刷新
     */
    private void initSwipeRefresh() {
        if (mSwipeRefresh != null) {
            SwipeRefreshHelper.init(mSwipeRefresh, new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    updateViews(true);
                }
            });
        }
    }

}
