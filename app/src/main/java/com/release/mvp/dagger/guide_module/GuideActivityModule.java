package com.release.mvp.dagger.guide_module;

import androidx.appcompat.app.AppCompatActivity;

import com.release.mvp.dagger.base.ActivityScope;
import com.release.mvp.dagger.base.BaseActivityModule;
import com.release.mvp.presenter.guide.GuideView;
import com.release.mvp.ui.guide.GuideActivity;

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
    abstract AppCompatActivity activity(GuideActivity activity);

    @Binds
    @ActivityScope
    abstract GuideView guideView(GuideActivity activity);
}
