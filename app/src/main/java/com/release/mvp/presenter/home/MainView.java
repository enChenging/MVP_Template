package com.release.mvp.presenter.home;

import com.release.mvp.presenter.base.BaseView;

/**
 * @author Mr.release
 * @create 2019/3/28
 * @Describe
 */
public interface MainView extends BaseView {

    void toggle();

    boolean closeDrawableLayout();
}
