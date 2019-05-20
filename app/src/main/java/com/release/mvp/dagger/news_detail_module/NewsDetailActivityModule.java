package com.release.mvp.dagger.news_detail_module;

import androidx.appcompat.app.AppCompatActivity;

import com.release.mvp.dagger.base.ActivityScope;
import com.release.mvp.dagger.base.BaseActivityModule;
import com.release.mvp.presenter.page.newsPage.detail.NewsDetailView;
import com.release.mvp.ui.page.news_page.NewsDetailActivity;

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
    abstract AppCompatActivity activity(NewsDetailActivity activity);

    @Binds
    @ActivityScope
    abstract NewsDetailView newsDetailView(NewsDetailActivity activity);
}
