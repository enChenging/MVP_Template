package com.release.mvp.presenter.page.newsPage.special;

import android.annotation.SuppressLint;

import com.alibaba.fastjson.JSON;
import com.release.mvp.bean.NewsItemInfoBean;
import com.release.mvp.bean.SpecialInfoBean;
import com.release.mvp.http.RetrofitHelper;
import com.release.mvp.presenter.BasePresenter;
import com.release.mvp.ui.adapter.item.SpecialItem;
import com.release.mvp.utils.LogUtils;

import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author Mr.release
 * @create 2019/4/15
 * @Describe
 */
public class NewsSpecialPresenter implements BasePresenter {

    private static final String TAG = NewsSpecialPresenter.class.getSimpleName();
    private final String mSpecialId;
    private final NewsSpecialView mView;

    public NewsSpecialPresenter(String specialId, NewsSpecialView view) {
        this.mSpecialId = specialId;
        this.mView = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadData(boolean isRefresh) {
        RetrofitHelper.getNewsSpecialAPI(mSpecialId)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.hideLoading();
                    }
                })
                .flatMap(new Function<SpecialInfoBean, ObservableSource<SpecialItem>>() {
                    @Override
                    public ObservableSource<SpecialItem> apply(SpecialInfoBean specialInfoBean) throws Exception {
                        String s = JSON.toJSONString(specialInfoBean);
                        LogUtils.i(TAG, "SpecialInfoBean: " + s);
                        mView.loadHead(specialInfoBean);
                        return _convertSpecialBeanToItem(specialInfoBean);
                    }
                })
                .toList()
                .toObservable()
                .compose(mView.<List<SpecialItem>>bindToLife())
                .subscribe(new Consumer<List<SpecialItem>>() {
                    @Override
                    public void accept(List<SpecialItem> specialItems) throws Exception {
                        mView.loadData(specialItems);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showNetError();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void loadMoreData() {

    }

    private Observable<SpecialItem> _convertSpecialBeanToItem(SpecialInfoBean specialBean) {
        // 这边 +1 是接口数据还有个 topicsplus 的字段可能是穿插在 topics 字段列表中间。这里没有处理 topicsplus
        final SpecialItem[] specialItems = new SpecialItem[specialBean.getTopics().size() + 1];


        return Observable.fromIterable(specialBean.getTopics())
                // 获取头部
                .doOnNext(new Consumer<SpecialInfoBean.TopicsBean>() {
                    @Override
                    public void accept(SpecialInfoBean.TopicsBean topicsEntity) throws Exception {
                        specialItems[topicsEntity.getIndex() - 1] = new SpecialItem(true,
                                topicsEntity.getIndex() + "/" + specialItems.length + " " + topicsEntity.getTname());
                    }
                })
                // 排序
                .toSortedList(new Comparator<SpecialInfoBean.TopicsBean>() {
                    @Override
                    public int compare(SpecialInfoBean.TopicsBean o1, SpecialInfoBean.TopicsBean o2) {
                        return o1.getIndex() - o2.getIndex();
                    }
                })
                .toObservable()
                // 拆分
                .flatMap(new Function<List<SpecialInfoBean.TopicsBean>, ObservableSource<SpecialInfoBean.TopicsBean>>() {
                    @Override
                    public ObservableSource<SpecialInfoBean.TopicsBean> apply(List<SpecialInfoBean.TopicsBean> topicsEntities) throws Exception {
                        return Observable.fromIterable(topicsEntities);
                    }
                })
                .flatMap(new Function<SpecialInfoBean.TopicsBean, ObservableSource<SpecialItem>>() {
                    @Override
                    public ObservableSource<SpecialItem> apply(SpecialInfoBean.TopicsBean topicsEntity) throws Exception {
                        // 转换并在每个列表项增加头部
                        return Observable.fromIterable(topicsEntity.getDocs())
                                .map(new Function<NewsItemInfoBean, SpecialItem>() {
                                    @Override
                                    public SpecialItem apply(NewsItemInfoBean newsItemInfoBean) throws Exception {
                                        return new SpecialItem(newsItemInfoBean);
                                    }
                                })
                                .startWith(specialItems[topicsEntity.getIndex() - 1]);

                    }
                });
    }
}
