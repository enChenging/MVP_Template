package com.release.mvp.presenter.page.videoPage.page;

import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.ui.page.video_page.VideoListFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;

/**
 * @author Mr.release
 * @create 2019/4/22
 * @Describe
 */
public class VideoPagePresenter extends BasePresenter<VideoPageView> {

    private final String[] VIDEO_ID = new String[]{
            "V9LG4B3A0", "V9LG4E6VR", "V9LG4CHOR", "00850FRB"
    };
    private final String[] VIDEO_TITLE = new String[]{
            "热点", "搞笑", "娱乐", "精品"
    };

    @Inject
    protected VideoPagePresenter(VideoPageView view) {
        super(view);
    }

    @Override
    public void loadData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < VIDEO_ID.length; i++) {
            fragments.add(VideoListFragment.newInstance(VIDEO_ID[i]));
        }

        view.loadDataView(fragments, VIDEO_TITLE);
    }
}
