package com.release.mvp.injector.main_module.news_page;

import com.release.mvp.injector.base.FragmentScope;
import com.release.mvp.presenter.page.newsPage.page.NewsPagePresenter;
import com.release.mvp.presenter.page.newsPage.page.NewsPagePresenterImpl;

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
    abstract NewsPagePresenter newsPagePresenter(NewsPagePresenterImpl newsPagePresenter);

}
