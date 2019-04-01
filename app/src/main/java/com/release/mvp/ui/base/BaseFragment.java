package com.release.mvp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.release.mvp.R;
import com.release.mvp.ui.home.MainActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public abstract class BaseFragment extends RxFragment implements UIIterfaceFrag {
    public MainActivity mActivity;
    private Unbinder mUnbinder;

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

        View view = LayoutInflater.from(getActivity()).inflate(getLayoutId(), null);
        mUnbinder = ButterKnife.bind(this, view);
        initView(view);
        initListener();
        initData();
        initCommonView(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void initCommonView(View view) {
        View back = view.findViewById(R.id.back);
        if (back != null) {
            back.setOnClickListener((v ->  getActivity().finish()));
        }
    }
}
