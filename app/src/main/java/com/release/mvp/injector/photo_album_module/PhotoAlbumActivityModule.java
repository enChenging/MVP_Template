package com.release.mvp.injector.photo_album_module;

import com.release.mvp.injector.base.ActivityScope;
import com.release.mvp.injector.base.BaseActivityModule;
import com.release.mvp.presenter.page.newsPage.photo_album.PhotoAlbumView;
import com.release.mvp.ui.adapter.PhotoSetAdapter;
import com.release.mvp.ui.page.news_page.PhotoAlbumActivity;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author Mr.release
 * @create 2019/4/20
 * @Describe
 */

@Module(includes = {BaseActivityModule.class, PhotoAlbumActivityPresenterModule.class})
public abstract class PhotoAlbumActivityModule {

    @Binds
    @ActivityScope
    abstract RxAppCompatActivity activity(PhotoAlbumActivity activity);

    @Binds
    @ActivityScope
    abstract PhotoAlbumView photoAlbumView(PhotoAlbumActivity activity);

    @Provides
    @ActivityScope
    static PhotoSetAdapter photoSetAdapter(PhotoAlbumActivity activity) {
        return new PhotoSetAdapter(activity);
    }
}