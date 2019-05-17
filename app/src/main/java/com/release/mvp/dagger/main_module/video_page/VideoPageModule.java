package com.release.mvp.dagger.main_module.video_page;

import com.release.mvp.dagger.base.BaseFragmentModule;
import com.release.mvp.dagger.base.ChildFragmentScope;
import com.release.mvp.dagger.base.Fragment;
import com.release.mvp.dagger.base.FragmentScope;
import com.release.mvp.presenter.page.videoPage.page.VideoPageView;
import com.release.mvp.ui.page.video_page.VideoListFragment;
import com.release.mvp.ui.page.video_page.VideoPage;
import com.trello.rxlifecycle3.components.support.RxFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Mr.release
 * @create 2019/4/18
 * @Describe
 */
@Module(includes = {BaseFragmentModule.class, VideoPagePresenterModule.class})
public abstract class VideoPageModule {

    @Binds
    @Fragment
    @FragmentScope
    abstract RxFragment fragment(VideoPage videoPage);

    @Binds
    @FragmentScope
    abstract VideoPageView videoPage(VideoPage videoPage);

    @ChildFragmentScope
    @ContributesAndroidInjector(modules = VideoListFragmentModule.class)
    abstract VideoListFragment videoListFragment();
}
