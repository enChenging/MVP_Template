package com.release.mvp.dagger.main_module.video_page;

import com.release.mvp.R;
import com.release.mvp.dagger.base.BaseChildFragmentModule;
import com.release.mvp.dagger.base.ChildFragment;
import com.release.mvp.dagger.base.ChildFragmentScope;
import com.release.mvp.presenter.page.videoPage.video_list.VideoListView;
import com.release.mvp.ui.adapter.VideoListAdapter;
import com.release.mvp.ui.page.video_page.VideoListFragment;
import com.trello.rxlifecycle3.components.support.RxFragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author Mr.release
 * @create 2019/4/22
 * @Describe
 */
@Module(includes = {BaseChildFragmentModule.class, VideoListPresenterModule.class})
abstract class VideoListFragmentModule {

    @Binds
    @ChildFragment
    @ChildFragmentScope
    abstract RxFragment fragment(VideoListFragment videoListFragment);

    @Binds
    @ChildFragmentScope
    abstract VideoListView videoListView(VideoListFragment videoListFragment);

    @Provides
    @ChildFragmentScope
    static VideoListAdapter videoListAdapter() {
        return new VideoListAdapter(R.layout.adapter_video_list, null);
    }
}
