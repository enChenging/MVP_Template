package com.release.mvp.dagger.base;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
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
    abstract Context activityContext(AppCompatActivity activity);

    @Provides
    @ActivityScope
    @ActivityFagmentManger
    static FragmentManager activityFragmentManager(AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
