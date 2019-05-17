package com.release.mvp.dagger.splash_module;

import com.release.mvp.dagger.base.FragmentScope;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.presenter.splash.SplashPresenter;

import dagger.Binds;
import dagger.Module;

/**
 * @author Mr.release
 * @create 2019/4/22
 * @Describe
 */
@Module
public abstract class SplashPresenterModule {
    @Binds
    @FragmentScope
    abstract BasePresenter splashPresenter(SplashPresenter splashPresenter);

}
