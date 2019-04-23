package com.release.mvp.injector.news_detail_module;

import com.release.mvp.injector.base.FragmentScope;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.presenter.page.newsPage.detail.NewsDetailPresenter;

import dagger.Binds;
import dagger.Module;

/**
 * @author Mr.release
 * @create 2019/4/22
 * @Describe
 */
@Module
public abstract class NewsDetialPresenterModule {
    @Binds
    @FragmentScope
    abstract BasePresenter newsDetailPresenter(NewsDetailPresenter newsDetailPresenter);

}