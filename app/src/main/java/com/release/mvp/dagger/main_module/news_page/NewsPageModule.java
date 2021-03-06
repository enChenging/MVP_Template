package com.release.mvp.dagger.main_module.news_page;

import androidx.fragment.app.Fragment;

import com.release.mvp.dagger.base.BaseFragmentModule;
import com.release.mvp.dagger.base.ChildFragmentScope;
import com.release.mvp.dagger.base.Fragmentq;
import com.release.mvp.dagger.base.FragmentScope;
import com.release.mvp.presenter.page.newsPage.page.NewsPageView;
import com.release.mvp.ui.page.news_page.NewsListFragment;
import com.release.mvp.ui.page.news_page.NewsPage;

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
    @Fragmentq
    @FragmentScope
    abstract Fragment fragment(NewsPage newsPage);

    @Binds
    @FragmentScope
    abstract NewsPageView newsPageView(NewsPage newsPage);

    @ChildFragmentScope
    @ContributesAndroidInjector(modules = NewsListFragmentModule.class)
    abstract NewsListFragment newsListFragmentInjector();


}
