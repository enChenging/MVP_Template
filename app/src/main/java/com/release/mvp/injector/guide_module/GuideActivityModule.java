package com.release.mvp.injector.guide_module;

import com.release.mvp.injector.base.ActivityScope;
import com.release.mvp.injector.base.BaseActivityModule;
import com.release.mvp.presenter.guide.GuideView;
import com.release.mvp.ui.guide.GuideActivity;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import dagger.Binds;
import dagger.Module;

/**
 * @author Mr.release
 * @create 2019/4/20
 * @Describe
 */
@Module(includes = {BaseActivityModule.class, GuidePresenterModule.class})
public abstract class GuideActivityModule {

    @Binds
    @ActivityScope
    abstract RxAppCompatActivity activity(GuideActivity activity);

    @Binds
    @ActivityScope
    abstract GuideView guideView(GuideActivity activity);
}
