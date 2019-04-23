package com.release.mvp.injector.main_module;

import com.release.mvp.injector.base.FragmentScope;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.presenter.home.MainPersenter;

import dagger.Binds;
import dagger.Module;

/**
 * @author Mr.release
 * @create 2019/4/22
 * @Describe
 */
@Module
public abstract class MainPresenterModule {
    @Binds
    @FragmentScope
    abstract BasePresenter mainPersenter(MainPersenter mainPersenter);

}
