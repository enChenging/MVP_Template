package com.release.mvp.presenter.page.newsPage.detail;

import android.annotation.SuppressLint;

import com.alibaba.fastjson.JSON;
import com.release.mvp.bean.NewsDetailInfoBean;
import com.release.mvp.http.RetrofitHelper;
import com.release.mvp.presenter.BasePresenter;
import com.release.mvp.utils.ListUtils;
import com.release.mvp.utils.LogUtils;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author Mr.release
 * @create 2019/4/15
 * @Describe
 */
public class NewsDetailPresenter implements BasePresenter {

    private static final String TAG = NewsDetailPresenter.class.getSimpleName();
    private String mNewsId;
    private final NewsDetailView mView;
    private static final String HTML_IMG_TEMPLATE = "<img src=\"http\" />";

    public NewsDetailPresenter(String newsId, NewsDetailView view) {
        this.mNewsId = newsId;
        this.mView = view;
    }


    public void setNewsId(String newsId) {
        mNewsId = newsId;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadData(boolean isRefresh) {

        RetrofitHelper.getNewsDetailAPI(mNewsId)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading();
                    }
                })
                .doOnNext(new Consumer<NewsDetailInfoBean>() {
                    @Override
                    public void accept(NewsDetailInfoBean newsDetailInfoBean) throws Exception {
                        String s = JSON.toJSONString(newsDetailInfoBean);
                        LogUtils.i(TAG, "accept: "+s);
                        _handleRichTextWithImg(newsDetailInfoBean);
                    }
                })
                .compose(mView.<NewsDetailInfoBean>bindToLife())
                .subscribe(new Consumer<NewsDetailInfoBean>() {
                    @Override
                    public void accept(NewsDetailInfoBean newsDetailInfoBean) throws Exception {
                        mView.loadData(newsDetailInfoBean);
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
