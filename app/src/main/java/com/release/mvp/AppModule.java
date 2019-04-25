package com.release.mvp;

import android.app.Application;

import com.release.mvp.dao.DaoMaster;
import com.release.mvp.dao.DaoSession;
import com.release.mvp.injector.base.ActivityScope;
import com.release.mvp.injector.guide_module.GuideActivityModule;
import com.release.mvp.injector.main_module.MainActivityModule;
import com.release.mvp.injector.news_detail_module.NewsDetailActivityModule;
import com.release.mvp.injector.news_special_module.NewsSpecialActivityModule;
import com.release.mvp.injector.photo_album_module.PhotoAlbumActivityModule;
import com.release.mvp.injector.splash_module.SplashActivityModule;
import com.release.mvp.injector.web_detail_module.WebDetailActivityModule;
import com.release.mvp.ui.guide.GuideActivity;
import com.release.mvp.ui.home.MainActivity;
import com.release.mvp.ui.page.news_page.NewsDetailActivity;
import com.release.mvp.ui.page.news_page.NewsSpecialActivity;
import com.release.mvp.ui.page.news_page.PhotoAlbumActivity;
import com.release.mvp.ui.web_detail.WebDetailActivity;
import com.release.mvp.ui.splash.SplashActivity;
import com.release.mvp.utils.baserx.RxBus;

import org.greenrobot.greendao.database.Database;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author Mr.release
 * @create 2019/4/18
 * @Describe
 */
@Module(includes = {AndroidInjectionModule.class, AndroidSupportInjectionModule.class})
abstract class AppModule {

    @Binds
    @Singleton
    abstract Application application(App app);

    @Provides
    @Singleton
    static RxBus rxBus() {
        return new RxBus();
    }

    @Provides
    @Singleton
    static DaoSession daoSession(App app) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(app, "dao_db");
        Database database = helper.getWritableDb();
        return new DaoMaster(database).newSession();
    }

    @ActivityScope
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity splashActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = GuideActivityModule.class)
    abstract GuideActivity guideActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = NewsDetailActivityModule.class)
    abstract NewsDetailActivity newsDetailActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = NewsSpecialActivityModule.class)
    abstract NewsSpecialActivity newsSpecialActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = PhotoAlbumActivityModule.class)
    abstract PhotoAlbumActivity photoAlbumActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = WebDetailActivityModule.class)
    abstract WebDetailActivity eventDetailActivityInjector();
}
