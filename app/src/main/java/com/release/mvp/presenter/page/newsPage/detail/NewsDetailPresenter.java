package com.release.mvp.presenter.page.newsPage.detail;

import android.annotation.SuppressLint;

import com.alibaba.fastjson.JSON;
import com.release.mvp.bean.NewsDetailInfoBean;
import com.release.mvp.http.RetrofitHelper;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.utils.ListUtils;
import com.release.mvp.utils.LogUtils;
import com.release.mvp.utils.baserx.CommonSubscriber;

import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * @author Mr.release
 * @create 2019/4/15
 * @Describe
 */
public class NewsDetailPresenter extends BasePresenter<NewsDetailView> {

    private static final String TAG = NewsDetailPresenter.class.getSimpleName();
    private static final String HTML_IMG_TEMPLATE = "<img src=\"http\" />";
    private String mNewsId;

    @Inject
    protected NewsDetailPresenter(NewsDetailView view) {
        super(view);
    }

    public void setNewsId(String newsId) {
        mNewsId = newsId;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadData() {

        RetrofitHelper.getNewsDetailAPI(mNewsId)
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        view.showLoading();
                    }
                })
                .doOnNext(new Consumer<NewsDetailInfoBean>() {
                    @Override
                    public void accept(NewsDetailInfoBean newsDetailInfoBean) throws Exception {
                        String s = JSON.toJSONString(newsDetailInfoBean);
                        LogUtils.i(TAG, "accept: " + s);
                        _handleRichTextWithImg(newsDetailInfoBean);
                    }
                })
                .compose(view.<NewsDetailInfoBean>bindToLife())
                .subscribeWith(new CommonSubscriber<NewsDetailInfoBean>() {
                    @Override
                    protected void _onNext(NewsDetailInfoBean newsDetailInfoBean) {
                        view.loadDataView(newsDetailInfoBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        view.showNetError();
                    }

                    @Override
                    protected void _onComplete() {
                        view.hideLoading();
                    }
                });
    }

    /**
     * 处理富文本包含图片的情况
     *
     * @param newsDetailBean 原始数据
     */
    private void _handleRichTextWithImg(NewsDetailInfoBean newsDetailBean) {
        if (!ListUtils.isEmpty(newsDetailBean.getImg())) {
            String body = newsDetailBean.getBody();
            for (NewsDetailInfoBean.ImgBean imgEntity : newsDetailBean.getImg()) {
                String ref = imgEntity.getRef();
                String src = imgEntity.getSrc();
                LogUtils.i(TAG, "imgEntity: " + imgEntity.toString());
                String img = HTML_IMG_TEMPLATE.replace("http", src);
                body = body.replaceAll(ref, img);
                LogUtils.i(TAG, "img: " + img);
                LogUtils.i(TAG, "body: " + body);
            }
            newsDetailBean.setBody(body);
        }
    }
}
