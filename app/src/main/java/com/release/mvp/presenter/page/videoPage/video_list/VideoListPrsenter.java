package com.release.mvp.presenter.page.videoPage.video_list;

import android.annotation.SuppressLint;

import com.release.mvp.dao.VideoInfo;
import com.release.mvp.http.RetrofitHelper;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.ui.page.video_page.VideoListFragment;
import com.release.mvp.utils.LogUtils;
import com.release.mvp.utils.ToastUtils;
import com.release.mvp.utils.baserx.CommonSubscriber;

import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * @author Mr.release
 * @create 2019/4/16
 * @Describe
 */
public class VideoListPrsenter extends BasePresenter<VideoListView> {

    private static final String TAG = VideoListPrsenter.class.getSimpleName();
    private int mPage = 0;
    @Inject
    VideoListFragment mVideoListFragment;

    @Inject
    protected VideoListPrsenter(VideoListView view) {
        super(view);
    }


    @SuppressLint("CheckResult")
    @Override
    public void loadData(boolean isRefresh) {
        RetrofitHelper
                .getVideoListAPI(mVideoListFragment.mVideoId, mPage)
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        if (!isRefresh) {
                            view.showLoading();
                        }
                    }
                })
                .compose(view.bindToLife())
                .subscribeWith(new CommonSubscriber<List<VideoInfo>>() {
                    @Override
                    protected void _onNext(List<VideoInfo> videoInfos) {
                        LogUtils.i(TAG, "accept: " + videoInfos);
                        view.loadDataView(videoInfos);
                        mPage++;
                    }

                    @Override
                    protected void _onError(String message) {
                        if (isRefresh) {
                            view.finishRefresh();
                            ToastUtils.show("刷新失败");
                        } else {
                            view.showNetError();
                        }
                    }

                    @Override
                    protected void _onComplete() {
                        LogUtils.i(TAG, "run: ");
                        if (isRefresh) {
                            view.finishRefresh();
                        } else {
                            view.hideLoading();
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadMoreData() {

        RetrofitHelper
                .getVideoListAPI(mVideoListFragment.mVideoId, mPage)
                .compose(view.bindToLife())
                .subscribeWith(new CommonSubscriber<List<VideoInfo>>() {
                    @Override
                    protected void _onNext(List<VideoInfo> videoInfos) {
                        LogUtils.i(TAG, "loadMoreData---accept: " + videoInfos);
                        view.loadMoreDataView(videoInfos);
                        mPage++;

                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtils.e(TAG, "loadMoreData---throwable: " + message);
                        view.loadNoDataView();
                    }

                    @Override
                    protected void _onComplete() {
                        LogUtils.i(TAG, "loadMoreData---run: ");
                    }
                });
    }
}
