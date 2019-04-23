package com.release.mvp.injector.main_module.news_page;

import com.release.mvp.injector.base.BaseFragmentModule;
import com.release.mvp.injector.base.ChildFragmentScope;
import com.release.mvp.injector.base.Fragment;
import com.release.mvp.injector.base.FragmentScope;
import com.release.mvp.presenter.page.newsPage.page.NewsPageView;
import com.release.mvp.ui.page.news_page.NewsListFragment;
import com.release.mvp.ui.page.news_page.NewsPage;
import com.trello.rxlifecycle3.components.support.RxFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Mr.release
 * @create 2019/4/18
 * @Describe
 */
@Module(includes = {BaseFragmentModule.class, NewsPagePresenterModule.class})
public abstract class NewsPageModule {

    @Binds
    @Fragment
    @FragmentScope
    abstract RxFragment fragment(NewsPage newsPage);

    @Binds
    @FragmentScope
    abstract NewsPageView newsPageView(NewsPage newsPage);

    @ChildFragmentScope
    @ContributesAndroidInjector(modules = NewsListFragmentModule.class)
    abstract NewsListFragment newsListFragmentInjector();


}
