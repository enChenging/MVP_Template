package com.release.mvp.dagger.main_module.video_page;

import com.release.mvp.dagger.base.FragmentScope;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.presenter.page.videoPage.video_list.VideoListPrsenter;

import dagger.Binds;
import dagger.Module;

/**
 * @author Mr.release
 * @create 2019/4/22
 * @Describe
 */
@Module
abstract class VideoListPresenterModule {
    @Binds
    @FragmentScope
    abstract BasePresenter newsPagePresenter(VideoListPrsenter videoListPrsenter);
}
