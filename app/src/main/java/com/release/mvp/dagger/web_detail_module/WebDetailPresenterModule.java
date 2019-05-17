package com.release.mvp.dagger.web_detail_module;

import com.release.mvp.dagger.base.ActivityScope;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.presenter.web_detail.WebDetailActivityPresenter;

import dagger.Binds;
import dagger.Module;

/**
 * @author Mr.release
 * @create 2019/4/25
 * @Describe
 */
@Module
public abstract class WebDetailPresenterModule {

    @Binds
    @ActivityScope
    abstract BasePresenter webDetailActivityPresenter(WebDetailActivityPresenter webDetailActivityPresenter);
}
