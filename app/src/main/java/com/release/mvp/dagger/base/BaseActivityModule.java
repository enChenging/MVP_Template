package com.release.mvp.dagger.base;

import android.content.Context;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import androidx.fragment.app.FragmentManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author Mr.release
 * @create 2019/4/19
 * @Describe
 */
@Module
public abstract class BaseActivityModule {

    @Binds
    @ActivityScope
    abstract Context activityContext(RxAppCompatActivity activity);

    @Provides
    @ActivityScope
    @ActivityFagmentManger
    static FragmentManager activityFragmentManager(RxAppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
