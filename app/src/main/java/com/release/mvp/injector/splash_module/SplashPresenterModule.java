package com.release.mvp.injector.splash_module;

import com.release.mvp.injector.base.FragmentScope;
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
