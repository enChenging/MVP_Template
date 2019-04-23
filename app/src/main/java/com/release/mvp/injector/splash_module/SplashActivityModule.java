package com.release.mvp.injector.splash_module;

import com.release.mvp.injector.base.ActivityScope;
import com.release.mvp.injector.base.BaseActivityModule;
import com.release.mvp.presenter.splash.SplashView;
import com.release.mvp.ui.splash.SplashActivity;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import dagger.Binds;
import dagger.Module;

/**
 * @author Mr.release
 * @create 2019/4/20
 * @Describe
 */

@Module(includes = {BaseActivityModule.class, SplashPresenterModule.class})
public abstract class SplashActivityModule {

    @Binds
    @ActivityScope
    abstract RxAppCompatActivity activity(SplashActivity activity);

    @Binds
    @ActivityScope
    abstract SplashView splashView(SplashActivity activity);
}
