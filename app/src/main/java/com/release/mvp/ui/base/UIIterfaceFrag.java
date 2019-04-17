package com.release.mvp.ui.base;

import android.view.View;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
interface UIIterfaceFrag {

    int getLayoutId();

    void initView(View view);

    void initListener();

    void updateViews(boolean isRefresh);

}
