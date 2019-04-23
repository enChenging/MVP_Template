package com.release.mvp.injector.main_module.news_page;


import com.release.mvp.injector.base.BaseChildFragmentModule;
import com.release.mvp.injector.base.ChildFragment;
import com.release.mvp.injector.base.ChildFragmentScope;
import com.release.mvp.presenter.page.newsPage.news_list.NewsListView;
import com.release.mvp.ui.adapter.NewsListAdapter;
import com.release.mvp.ui.page.news_page.NewsListFragment;
import com.trello.rxlifecycle3.components.support.RxFragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author Mr.release
 * @create 2019/4/20
 * @Describe
 */
@Module(includes = {BaseChildFragmentModule.class, NewsListPresenterModule.class})
public abstract class NewsListFragmentModule {

    @Binds
    @ChildFragment
    @ChildFragmentScope
    abstract RxFragment fragment(NewsListFragment newsListFragment);

    @Binds
    @ChildFragmentScope
    abstract NewsListView newsListView(NewsListFragment newsListFragment);

    @Provides
    @ChildFragmentScope
    static NewsListAdapter newsListAdapter() {
        return new NewsListAdapter(null);
    }
}
