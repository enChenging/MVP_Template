package com.release.mvp.presenter.page.newsPage.photo_album;

import com.release.mvp.bean.PhotoSetInfoBean;
import com.release.mvp.presenter.base.BaseView;
import com.release.mvp.utils.response.BaseResponse;

/**
 * @author Mr.release
 * @create 2019/4/16
 * @Describe
 */
public interface PhotoAlbumView extends BaseView {

    void loadPhotoDataView(PhotoSetInfoBean photoSetBean);
}
