package com.release.mvp.presenter.page.newsPage.news_list;

import android.annotation.SuppressLint;

import androidx.lifecycle.LifecycleOwner;

import com.release.mvp.bean.NewsInfoBean;
import com.release.mvp.http.RetrofitHelper;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.ui.adapter.item.NewsMultiItem;
import com.release.mvp.utils.LogUtils;
import com.release.mvp.utils.NewsUtils;
import com.release.mvp.utils.ToastUtils;
import com.release.mvp.utils.baserx.CommonSubscriber;
import com.release.mvp.utils.baserx.RxUtil;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * @author Mr.release
 * @create 2019/3/29
 * @Describe
 */
public class NewsListPresenter extends BasePresenter<NewsListView> {

    private static final String TAG = NewsListPresenter.class.getSimpleName();
    private int mPage = 0;
    public String mNewsId;

    @Inject
    public NewsListPresenter(NewsListView view) {
        super(view);
    }


    public void setNewsId(String newsId) {
        mNewsId = newsId;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadData(boolean isRefresh) {

        LogUtils.e("NewsListFragment", "loadData---mNewsId: " + mNewsId);

        RetrofitHelper
                .getImportantNewAPI(mNewsId, mPage)
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        if (!isRefresh) {
                            view.showLoading();
                        }
                    }
                })
                .filter(new Predicate<NewsInfoBean>() {
                    @Override
                    public boolean test(NewsInfoBean newsInfo) throws Exception {
                        if (NewsUtils.isAbNews(newsInfo))
                            view.loadAdDataView(newsInfo);
                        return !NewsUtils.isAbNews(newsInfo);
                    }
                })
                .compose(observableTransformer)
                .as(RxUtil.bindLifecycle((LifecycleOwner) view))
                .subscribeWith(new CommonSubscriber<List<NewsMultiItem>>() {
                    @Override
                    protected void _onNext(List<NewsMultiItem> newsMultiItems) {
                        LogUtils.d(TAG, "_onNext: ");
                        view.loadDataView(newsMultiItems);
                        mPage++;
                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtils.d(TAG, "_onError: " + message);
                        if (isRefresh) {
                            view.finishRefresh();
                            ToastUtils.show("刷新失败");
                        } else {
                            view.showNetError();
                        }
                    }

                    @Override
                    protected void _onComplete() {
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
        LogUtils.i(TAG, "loadMoreData: ");
        RetrofitHelper
                .getImportantNewAPI("T1348647909107", mPage)
                .compose(observableTransformer)
                .as(RxUtil.bindLifecycle((LifecycleOwner) view))
                .subscribeWith(new CommonSubscriber<List<NewsMultiItem>>() {
                    @Override
                    protected void _onNext(List<NewsMultiItem> newsMultiItems) {
                        LogUtils.d(TAG, "_onNext: ");
                        view.loadMoreDataView(newsMultiItems);
                        mPage++;
                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtils.d(TAG, "_onError: " + message);
                        view.loadNoDataView();
                    }

                    @Override
                    protected void _onComplete() {
                        LogUtils.d(TAG, "_onComplete: ");
                    }
                });
    }


    private FlowableTransformer<NewsInfoBean, List<NewsMultiItem>> observableTransformer = new FlowableTransformer<NewsInfoBean, List<NewsMultiItem>>() {

        @Override
        public Publisher<List<NewsMultiItem>> apply(Flowable<NewsInfoBean> upstream) {

            return upstream
                    .map(new Function<NewsInfoBean, NewsMultiItem>() {
                        @Override
                        public NewsMultiItem apply(NewsInfoBean newsInfo) throws Exception {

                            if (NewsUtils.isNewsPhotoSet(newsInfo.getSkipType())) {
                                return new NewsMultiItem(NewsMultiItem.ITEM_TYPE_PHOTO_SET, newsInfo);
                            }
                            return new NewsMultiItem(NewsMultiItem.ITEM_TYPE_NORMAL, newsInfo);
                        }
                    })
                    .toList()
                    .toFlowable();
        }
    };
}
