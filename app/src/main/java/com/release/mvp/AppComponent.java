package com.release.mvp;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * @author Mr.release
 * @create 2019/4/18
 * @Describe
 */
@Singleton
@Component(modules = AppModule.class )
interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {

    }
}
