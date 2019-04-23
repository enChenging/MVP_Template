package com.release.mvp.injector.main_module.kinds_page;

import com.release.mvp.injector.base.FragmentScope;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.presenter.page.kindsPage.KindsPagePresenter;

import dagger.Binds;
import dagger.Module;

/**
 * @author Mr.release
 * @create 2019/4/22
 * @Describe
 */
@Module
public abstract class KindsPagePresenterModule {
    @Binds
    @FragmentScope
    abstract BasePresenter kindsPageePresenter(KindsPagePresenter kindsPagePresenter);
}
