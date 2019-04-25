package com.release.mvp.injector.web_detail_module;

import com.release.mvp.injector.base.ActivityScope;
import com.release.mvp.injector.base.BaseActivityModule;
import com.release.mvp.presenter.web_detail.WebDetailActivityView;
import com.release.mvp.ui.web_detail.WebDetailActivity;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

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
    abstract RxAppCompatActivity activity(WebDetailActivity activity);

    @Binds
    @ActivityScope
    abstract WebDetailActivityView webDetailActivityView(WebDetailActivity activity);

}
