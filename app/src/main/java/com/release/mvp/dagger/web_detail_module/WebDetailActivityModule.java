package com.release.mvp.dagger.web_detail_module;

import androidx.appcompat.app.AppCompatActivity;

import com.release.mvp.dagger.base.ActivityScope;
import com.release.mvp.dagger.base.BaseActivityModule;
import com.release.mvp.presenter.web_detail.WebDetailActivityView;
import com.release.mvp.ui.web_detail.WebDetailActivity;

import dagger.Binds;
import dagger.Module;

/**
 * @author Mr.release
 * @create 2019/4/25
 * @Describe
 */
@Module(includes = {BaseActivityModule.class, WebDetailPresenterModule.class})
public abstract class WebDetailActivityModule {
    @Binds
    @ActivityScope
    abstract AppCompatActivity activity(WebDetailActivity activity);

    @Binds
    @ActivityScope
    abstract WebDetailActivityView webDetailActivityView(WebDetailActivity activity);

}
