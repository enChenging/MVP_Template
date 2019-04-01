package com.release.mvp.presenter.page.recommendPage;

import com.release.mvp.http.RetrofitHelper;
import com.release.mvp.modle.RecommendPageBean;
import com.release.mvp.ui.page.recommendPage.RecommendPage;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Mr.release
 * @create 2019/4/1
 * @Describe
 */
public class RecommendPagePresenter {

    private RecommendPageView mView;
    public List<RecommendPageBean.DataBean> list = new ArrayList<>();

    public RecommendPagePresenter(RecommendPageView view) {
        this.mView = view;
    }


    public void loadData(RecommendPage fragment) {
        if (list.size() != 0) return;
        RetrofitHelper.getRecommendAPI()
                .getRecommendData()
                .compose(fragment.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                            list.addAll(bean.getData());
                            mView.finishTask();
                        }

                        , throwable -> {
                            throwable.printStackTrace();
                            mView.showEmptyView();
                        }
                );
    }
}
