package com.release.mvp.presenter.page.newsPage.photo_album;

import android.annotation.SuppressLint;

import com.release.mvp.bean.PhotoSetInfoBean;
import com.release.mvp.http.RetrofitHelper;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.utils.LogUtils;
import com.release.mvp.utils.baserx.CommonSubscriber;

import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * @author Mr.release
 * @create 2019/4/16
 * @Describe
 */
public class PhotoAlbumPresenter extends BasePresenter<PhotoAlbumView> {

    private static final String TAG = PhotoAlbumPresenter.class.getSimpleName();
    private String mPhotoSetId;

    @Inject
    protected PhotoAlbumPresenter(PhotoAlbumView view) {
        super(view);
    }

    public void setPhotoSetId(String photoSetId) {
        mPhotoSetId = photoSetId;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadData() {
        LogUtils.i(TAG, "loadData---mPhotoSetId: " + mPhotoSetId);
        RetrofitHelper.getPhotoAlbumAPI(mPhotoSetId)
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        view.showLoading();
                    }
                })
                .compose(view.bindToLife())
                .subscribeWith(new CommonSubscriber<PhotoSetInfoBean>() {
                    @Override
                    protected void _onNext(PhotoSetInfoBean photoSetInfoBean) {
                        LogUtils.i(TAG, "_onNext: " + photoSetInfoBean);
                        view.loadPhotoDataView(photoSetInfoBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtils.i(TAG, "throwable: " + message);
                        view.showNetError();
                    }

                    @Override
                    protected void _onComplete() {
                        view.hideLoading();
                    }
                });
    }

}
