package com.release.mvp.injector.guide_module;

import com.release.mvp.injector.base.FragmentScope;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.presenter.guide.GuidePersenter;

import dagger.Binds;
import dagger.Module;

/**
 * @author Mr.release
 * @create 2019/4/22
 * @Describe
 */
@Module
public abstract class GuidePresenterModule {
    @Binds
    @FragmentScope
    abstract BasePresenter guidePersenter(GuidePersenter guidePersenter);

}
