package com.release.mvp.dagger.splash_module;

import androidx.appcompat.app.AppCompatActivity;

import com.release.mvp.dagger.base.ActivityScope;
import com.release.mvp.dagger.base.BaseActivityModule;
import com.release.mvp.presenter.splash.SplashView;
import com.release.mvp.ui.splash.SplashActivity;

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
    abstract AppCompatActivity activity(SplashActivity activity);

    @Binds
    @ActivityScope
    abstract SplashView splashView(SplashActivity activity);
}
