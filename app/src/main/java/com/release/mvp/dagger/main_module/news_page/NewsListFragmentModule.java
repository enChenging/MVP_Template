package com.release.mvp.dagger.main_module.news_page;


import androidx.fragment.app.Fragment;

import com.release.mvp.dagger.base.BaseChildFragmentModule;
import com.release.mvp.dagger.base.ChildFragment;
import com.release.mvp.dagger.base.ChildFragmentScope;
import com.release.mvp.presenter.page.newsPage.news_list.NewsListView;
import com.release.mvp.ui.adapter.NewsListAdapter;
import com.release.mvp.ui.page.news_page.NewsListFragment;

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
    abstract Fragment fragment(NewsListFragment newsListFragment);

    @Binds
    @ChildFragmentScope
    abstract NewsListView newsListView(NewsListFragment newsListFragment);

    @Provides
    @ChildFragmentScope
    static NewsListAdapter newsListAdapter() {
        return new NewsListAdapter(null);
    }
}
