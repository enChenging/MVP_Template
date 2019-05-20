package com.release.mvp.presenter.page.newsPage.special;

import android.annotation.SuppressLint;

import androidx.lifecycle.LifecycleOwner;

import com.alibaba.fastjson.JSON;
import com.release.mvp.bean.NewsItemInfoBean;
import com.release.mvp.bean.SpecialInfoBean;
import com.release.mvp.http.RetrofitHelper;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.ui.adapter.item.SpecialItem;
import com.release.mvp.utils.LogUtils;
import com.release.mvp.utils.baserx.CommonSubscriber;
import com.release.mvp.utils.baserx.RxUtil;

import org.reactivestreams.Publisher;

import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author Mr.release
 * @create 2019/4/15
 * @Describe
 */
public class NewsSpecialPresenter extends BasePresenter<NewsSpecialView> {

    private static final String TAG = NewsSpecialPresenter.class.getSimpleName();

    @Inject
    protected NewsSpecialPresenter(NewsSpecialView view) {
        super(view);
    }

    private String specialId;

    public void setSpeicalId(String specialId) {
        this.specialId = specialId;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadData() {
        RetrofitHelper
                .getNewsSpecialAPI(specialId)
                .doOnSubscribe(subscription -> view.hideLoading())
                .flatMap(new Function<SpecialInfoBean, Publisher<SpecialItem>>() {
                    @Override
                    public Publisher<SpecialItem> apply(SpecialInfoBean specialInfoBean) throws Exception {
                        String s = JSON.toJSONString(specialInfoBean);
                        LogUtils.i(TAG, "SpecialInfoBean: " + s);
                        view.loadHeadView(specialInfoBean);
                        return _convertSpecialBeanToItem(specialInfoBean);
                    }
                })
                .toList()
                .toFlowable()
                .as(RxUtil.bindLifecycle((LifecycleOwner) view))
                .subscribeWith(new CommonSubscriber<List<SpecialItem>>() {
                    @Override
                    protected void _onNext(List<SpecialItem> specialItems) {
                        LogUtils.i(TAG, "_onNext: ");
                        view.loadDataView(specialItems);
                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtils.i(TAG, "_onError: ");
                        view.showNetError();
                    }

                    @Override
                    protected void _onComplete() {
                        LogUtils.i(TAG, "_onComplete: ");
                        view.hideLoading();
                    }
                });
    }

    private Flowable<SpecialItem> _convertSpecialBeanToItem(SpecialInfoBean specialBean) {
        // 这边 +1 是接口数据还有个 topicsplus 的字段可能是穿插在 topics 字段列表中间。这里没有处理 topicsplus
        final SpecialItem[] specialItems = new SpecialItem[specialBean.getTopics().size() + 1];


        return Flowable
                .fromIterable(specialBean.getTopics())
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
                .toFlowable()
                // 拆分
                .flatMap(new Function<List<SpecialInfoBean.TopicsBean>, Publisher<SpecialInfoBean.TopicsBean>>() {
                    @Override
                    public Publisher<SpecialInfoBean.TopicsBean> apply(List<SpecialInfoBean.TopicsBean> topicsEntities) throws Exception {
                        return Flowable.fromIterable(topicsEntities);
                    }
                })
                .flatMap(new Function<SpecialInfoBean.TopicsBean, Publisher<SpecialItem>>() {
                    @Override
                    public Publisher<SpecialItem> apply(SpecialInfoBean.TopicsBean topicsEntity) throws Exception {
                        // 转换并在每个列表项增加头部
                        return Flowable.fromIterable(topicsEntity.getDocs())
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
