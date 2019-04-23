package com.release.mvp.injector.main_module;

import com.release.mvp.injector.base.ActivityScope;
import com.release.mvp.injector.base.BaseActivityModule;
import com.release.mvp.injector.base.FragmentScope;
import com.release.mvp.injector.main_module.kinds_page.KindsPageModule;
import com.release.mvp.injector.main_module.news_page.NewsPageModule;
import com.release.mvp.injector.main_module.recommend_page.RecommendPageModule;
import com.release.mvp.injector.main_module.video_page.VideoPageModule;
import com.release.mvp.presenter.home.MainView;
import com.release.mvp.ui.home.MainActivity;
import com.release.mvp.ui.page.kinds_page.KindsPage;
import com.release.mvp.ui.page.news_page.NewsPage;
import com.release.mvp.ui.page.recommend_page.RecommendPage;
import com.release.mvp.ui.page.video_page.VideoPage;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Mr.release
 * @create 2019/4/18
 * @Describe
 */
@Module(includes = {BaseActivityModule.class, MainPresenterModule.class})
public abstract class MainActivityModule {

    @Binds
    @ActivityScope
    abstract RxAppCompatActivity activity(MainActivity activity);

    @Binds
    @ActivityScope
    abstract MainView mainView(MainActivity activity);

    @FragmentScope
    @ContributesAndroidInjector(modules = NewsPageModule.class)
    abstract NewsPage newsPageInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = VideoPageModule.class)
    abstract VideoPage videoPageInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = RecommendPageModule.class)
    abstract RecommendPage recommendPageInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = KindsPageModule.class)
    abstract KindsPage kindsPageInjector();


}
