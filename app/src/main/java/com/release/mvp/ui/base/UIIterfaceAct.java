package com.release.mvp.ui.base;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
interface UIIterfaceAct {

    int getLayoutId();

    void initView();

    void initListener();

    void updateViews(boolean isRefresh);

}
