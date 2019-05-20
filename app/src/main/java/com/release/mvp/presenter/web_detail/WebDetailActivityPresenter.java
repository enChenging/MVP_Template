package com.release.mvp.presenter.web_detail;

import android.annotation.SuppressLint;

import androidx.lifecycle.LifecycleOwner;

import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.utils.baserx.RxUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * @author Mr.release
 * @create 2019/4/25
 * @Describe
 */
public class WebDetailActivityPresenter extends BasePresenter<WebDetailActivityView> {

    @Inject
    protected WebDetailActivityPresenter(WebDetailActivityView view) {
        super(view);
    }

    @Override
    public void loadData() {
        view.showLoading();
    }

    @SuppressLint("CheckResult")
    public void loadFinish() {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .as(RxUtil.bindLifecycle((LifecycleOwner) view))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        view.hideLoading();
                    }
                });
    }
}
