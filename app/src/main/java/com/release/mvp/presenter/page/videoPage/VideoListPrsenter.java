package com.release.mvp.presenter.page.videoPage;

import android.annotation.SuppressLint;

import com.release.mvp.dao.VideoInfo;
import com.release.mvp.http.RetrofitHelper;
import com.release.mvp.presenter.BasePresenter;
import com.release.mvp.utils.LogUtils;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author Mr.release
 * @create 2019/4/16
 * @Describe
 */
public class VideoListPrsenter implements BasePresenter {

    private static final String TAG = VideoListPrsenter.class.getSimpleName();
    final private VideoListView mView;
    final private String mVideoId;
    private int mPage = 0;

    public VideoListPrsenter(String videoId, VideoListView view) {
        this.mView = view;
        this.mVideoId = videoId;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadData(boolean isRefresh) {
        RetrofitHelper.getVideoListAPI(mVideoId, mPage)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading();
                    }
                })
                .compose(mView.bindToLife())
                .subscribe(new Consumer<List<VideoInfo>>() {
                               @Override
                               public void accept(List<VideoInfo> videoInfoBeans) throws Exception {
                                   LogUtils.i(TAG, "accept: " + videoInfoBeans);
                                   mView.loadData(videoInfoBeans);
                                   mPage++;
                               }
                           }
                        , new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                LogUtils.e(TAG, "throwable: " + throwable.toString());
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

    @SuppressLint("CheckResult")
    @Override
    public void loadMoreData() {

        RetrofitHelper.getVideoListAPI(mVideoId, mPage)
                .compose(mView.bindToLife())
                .subscribe(new Consumer<List<VideoInfo>>() {
                               @Override
                               public void accept(List<VideoInfo> videoInfoBeans) throws Exception {
                                   LogUtils.i(TAG, "loadMoreData---accept: " + videoInfoBeans);
                                   mView.loadData(videoInfoBeans);
                                   mPage++;
                               }
                           }
                        , new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                LogUtils.e(TAG, "loadMoreData---throwable: " + throwable.toString());
                                mView.showNetError();
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                LogUtils.i(TAG, "loadMoreData---run: ");
                            }
                        });
    }
}
