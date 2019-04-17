package com.release.mvp.presenter;

import com.trello.rxlifecycle3.LifecycleTransformer;

/**
 * @author Mr.release
 * @create 2019/4/1
 * @Describe
 */
public interface BaseView {

    void showLoading();

    void hideLoading();

    void showNetError();

    void finishRefresh();

    <T> LifecycleTransformer<T> bindToLife();
}
