package com.release.mvp.presenter.page.recommendPage;

import android.annotation.SuppressLint;

import androidx.lifecycle.LifecycleOwner;

import com.release.mvp.bean.RecommendPageBean;
import com.release.mvp.http.RetrofitHelper;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.utils.baserx.CommonSubscriber;
import com.release.mvp.utils.baserx.RxUtil;

import javax.inject.Inject;

/**
 * @author Mr.release
 * @create 2019/4/1
 * @Describe
 */
public class RecommendPagePresenter extends BasePresenter<RecommendPageView> {

    @Inject
    protected RecommendPagePresenter(RecommendPageView view) {
        super(view);
    }


    @SuppressLint("CheckResult")
    @Override
    public void loadData() {
        RetrofitHelper
                .getRecommendData("4a0090627cf07a50def18da875165740", 20)
                .doOnSubscribe(subscription -> view.showLoading())
                .as(RxUtil.bindLifecycle((LifecycleOwner) view))
                .subscribeWith(new CommonSubscriber<RecommendPageBean>() {
                    @Override
                    protected void _onNext(RecommendPageBean recommendPageBean) {
                        view.loadDataView(recommendPageBean.getNewslist());
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

}
