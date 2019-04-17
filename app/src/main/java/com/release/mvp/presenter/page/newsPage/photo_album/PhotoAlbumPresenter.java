package com.release.mvp.presenter.page.newsPage.photo_album;

import android.annotation.SuppressLint;

import com.alibaba.fastjson.JSON;
import com.release.mvp.bean.PhotoSetInfoBean;
import com.release.mvp.http.RetrofitHelper;
import com.release.mvp.presenter.BasePresenter;
import com.release.mvp.utils.LogUtils;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author Mr.release
 * @create 2019/4/16
 * @Describe
 */
public class PhotoAlbumPresenter implements BasePresenter {

    private static final String TAG = PhotoAlbumPresenter.class.getSimpleName();
    private final PhotoAlbumView mView;
    private final String mPhotoSetId;

    public PhotoAlbumPresenter(PhotoAlbumView view, String photoSetId) {
        mView = view;
        mPhotoSetId = photoSetId;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadData(boolean isRefresh) {
        LogUtils.i(TAG, "loadData: " + mPhotoSetId);
        RetrofitHelper.getPhotoAlbumAPI(mPhotoSetId)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        LogUtils.i(TAG, "doOnSubscribe: ");
                        mView.showLoading();
                    }
                })
                .compose(mView.bindToLife())
                .subscribe(new Consumer<PhotoSetInfoBean>() {
                    @Override
                    public void accept(PhotoSetInfoBean photoSetInfoBean) throws Exception {
                        mView.loadPhotoData(photoSetInfoBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.i(TAG, "throwable: ");
                        mView.showNetError();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.i(TAG, "run: ");
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void loadMoreData() {

    }
}
