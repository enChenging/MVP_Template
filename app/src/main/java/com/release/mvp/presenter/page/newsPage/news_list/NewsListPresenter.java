package com.release.mvp.presenter.page.newsPage.news_list;

import android.annotation.SuppressLint;

import com.release.mvp.bean.NewsInfoBean;
import com.release.mvp.http.RetrofitHelper;
import com.release.mvp.presenter.BasePresenter;
import com.release.mvp.ui.adapter.item.NewsMultiItem;
import com.release.mvp.utils.LogUtils;
import com.release.mvp.utils.NewsUtils;
import com.release.mvp.utils.ToastUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * @author Mr.release
 * @create 2019/3/29
 * @Describe
 */
public class NewsListPresenter implements BasePresenter {

    private static final String TAG = NewsListPresenter.class.getSimpleName();
    private NewsListView mView;
    private String mNewsId;
    private int mPage = 0;

    public NewsListPresenter(NewsListView view, String newsId) {
        this.mView = view;
        this.mNewsId = newsId;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadData(boolean isRefresh) {
        RetrofitHelper.getImportantNewAPI(mNewsId, mPage)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (!isRefresh) {
                            mView.showLoading();
                        }
                    }
                })
                .filter(new Predicate<NewsInfoBean>() {
                    @Override
                    public boolean test(NewsInfoBean newsInfo) throws Exception {
                        if (NewsUtils.isAbNews(newsInfo))
                            mView.loadAdData(newsInfo);
                        return !NewsUtils.isAbNews(newsInfo);
                    }
                })
                .compose(observableTransformer)
                .subscribe(new Consumer<List<NewsMultiItem>>() {
                    @Override
                    public void accept(List<NewsMultiItem> newsMultiItems) throws Exception {
                        LogUtils.d(TAG, "loadData--accept: ");
                        mView.loadData(newsMultiItems);
                        mPage++;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.d(TAG, "loadData--throwable: " + throwable.toString());
                        if (isRefresh) {
                            mView.finishRefresh();
                            ToastUtils.showToast("刷新失败");
                        } else {
                            mView.showNetError();
                        }
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.d(TAG, "loadData--Action: ");
                        if (isRefresh) {
                            mView.finishRefresh();
                        } else {
                            mView.hideLoading();
                        }
                    }
                });
    }


    @SuppressLint("CheckResult")
    @Override
    public void loadMoreData() {
        LogUtils.i(TAG, "loadMoreData: ");
        RetrofitHelper.getImportantNewAPI("T1348647909107", mPage)
                .compose(observableTransformer)
                .subscribe(new Consumer<List<NewsMultiItem>>() {
                    @Override
                    public void accept(List<NewsMultiItem> newsMultiItems) throws Exception {
                        LogUtils.d(TAG, "loadMoreData--accept: ");
                        mView.loadMoreData(newsMultiItems);
                        mPage++;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.d(TAG, "loadMoreData--throwable: " + throwable.toString());
                        mView.loadNoData();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.d(TAG, "loadMoreData--Action: ");
                    }
                });
    }


    private ObservableTransformer<NewsInfoBean, List<NewsMultiItem>> observableTransformer = new ObservableTransformer<NewsInfoBean, List<NewsMultiItem>>() {
        @Override
        public ObservableSource<List<NewsMultiItem>> apply(Observable<NewsInfoBean> upstream) {

            return upstream.map(new Function<NewsInfoBean, NewsMultiItem>() {
                @Override
                public NewsMultiItem apply(NewsInfoBean newsInfo) throws Exception {

                    if (NewsUtils.isNewsPhotoSet(newsInfo.getSkipType())) {
                        return new NewsMultiItem(NewsMultiItem.ITEM_TYPE_PHOTO_SET, newsInfo);
                    }
                    return new NewsMultiItem(NewsMultiItem.ITEM_TYPE_NORMAL, newsInfo);
                }
            }).toList().toObservable().compose(mView.<List<NewsMultiItem>>bindToLife());
        }
    };
}
