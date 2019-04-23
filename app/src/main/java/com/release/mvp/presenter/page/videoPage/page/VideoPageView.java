package com.release.mvp.presenter.page.videoPage.page;

import com.release.mvp.presenter.base.BaseView;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

/**
 * @author Mr.release
 * @create 2019/4/22
 * @Describe
 */
public interface VideoPageView extends BaseView {
    void loadDataView(ArrayList<Fragment> fragments, String[] strings);
}
