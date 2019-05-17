package com.release.mvp.dagger.main_module.news_page;

import com.release.mvp.dagger.base.ChildFragmentScope;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.presenter.page.newsPage.news_list.NewsListPresenter;

import dagger.Binds;
import dagger.Module;

/**
 * @author Mr.release
 * @create 2019/4/20
 * @Describe
 */
@Module
public abstract class NewsListPresenterModule {

    @Binds
    @ChildFragmentScope
    abstract BasePresenter newsListPresente(NewsListPresenter newsListPresenter);
}
