package com.release.mvp.dagger.main_module.news_page;

import com.release.mvp.dagger.base.FragmentScope;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.presenter.page.newsPage.page.NewsPagePresenter;

import dagger.Binds;
import dagger.Module;


/**
 * @author Mr.release
 * @create 2019/4/19
 * @Describe
 */
@Module
public abstract class NewsPagePresenterModule {

    @Binds
    @FragmentScope
    abstract BasePresenter newsPagePresenter(NewsPagePresenter newsPagePresenter);

}
