package com.release.mvp.injector.main_module.recommend_page;

import com.release.mvp.injector.base.FragmentScope;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.presenter.page.recommendPage.RecommendPagePresenter;

import dagger.Binds;
import dagger.Module;

/**
 * @author Mr.release
 * @create 2019/4/22
 * @Describe
 */
@Module
public abstract class RecommendPagePresenterModule {

    @Binds
    @FragmentScope
    abstract BasePresenter recommendPagePresenter(RecommendPagePresenter recommendPagePresenter);
}
