package com.release.mvp.dagger.photo_album_module;

import com.release.mvp.dagger.base.FragmentScope;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.presenter.page.newsPage.photo_album.PhotoAlbumPresenter;

import dagger.Binds;
import dagger.Module;

/**
 * @author Mr.release
 * @create 2019/4/22
 * @Describe
 */
@Module
public abstract class PhotoAlbumActivityPresenterModule {
    @Binds
    @FragmentScope
    abstract BasePresenter photoAlbumPresenter(PhotoAlbumPresenter photoAlbumPresenter);

}
