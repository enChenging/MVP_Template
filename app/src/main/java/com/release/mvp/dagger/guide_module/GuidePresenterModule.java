package com.release.mvp.dagger.guide_module;

import com.release.mvp.dagger.base.FragmentScope;
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
