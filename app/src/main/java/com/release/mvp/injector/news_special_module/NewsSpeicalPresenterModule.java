package com.release.mvp.injector.news_special_module;

import com.release.mvp.injector.base.FragmentScope;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.presenter.page.newsPage.special.NewsSpecialPresenter;

import dagger.Binds;
import dagger.Module;

/**
 * @author Mr.release
 * @create 2019/4/22
 * @Describe
 */
@Module
public abstract class NewsSpeicalPresenterModule {
    @Binds
    @FragmentScope
    abstract BasePresenter newsSpecialPresenter(NewsSpecialPresenter newsSpecialPresenter);

}
