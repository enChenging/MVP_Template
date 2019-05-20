package com.release.mvp.dagger.news_special_module;

import androidx.appcompat.app.AppCompatActivity;

import com.release.mvp.R;
import com.release.mvp.dagger.base.ActivityScope;
import com.release.mvp.dagger.base.BaseActivityModule;
import com.release.mvp.presenter.page.newsPage.special.NewsSpecialView;
import com.release.mvp.ui.adapter.NewsSpecialAdapter;
import com.release.mvp.ui.page.news_page.NewsSpecialActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author Mr.release
 * @create 2019/4/20
 * @Describe
 */

@Module(includes = {BaseActivityModule.class, NewsSpeicalPresenterModule.class})
public abstract class NewsSpecialActivityModule {

    @Binds
    @ActivityScope
    abstract AppCompatActivity activity(NewsSpecialActivity activity);

    @Binds
    @ActivityScope
    abstract NewsSpecialView newsSpecialView(NewsSpecialActivity activity);

    @Provides
    @ActivityScope
    static NewsSpecialAdapter newsSpecialAdapter() {
        return new NewsSpecialAdapter(R.layout.adapter_news_list, R.layout.adapter_special_head, null);
    }
}
