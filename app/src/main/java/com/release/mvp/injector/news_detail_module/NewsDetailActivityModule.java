package com.release.mvp.injector.news_detail_module;

import com.release.mvp.injector.base.ActivityScope;
import com.release.mvp.injector.base.BaseActivityModule;
import com.release.mvp.presenter.page.newsPage.detail.NewsDetailView;
import com.release.mvp.ui.page.news_page.NewsDetailActivity;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import dagger.Binds;
import dagger.Module;

/**
 * @author Mr.release
 * @create 2019/4/20
 * @Describe
 */

@Module(includes = {BaseActivityModule.class, NewsDetialPresenterModule.class})
public abstract class NewsDetailActivityModule {

    @Binds
    @ActivityScope
    abstract RxAppCompatActivity activity(NewsDetailActivity activity);

    @Binds
    @ActivityScope
    abstract NewsDetailView newsDetailView(NewsDetailActivity activity);
}
