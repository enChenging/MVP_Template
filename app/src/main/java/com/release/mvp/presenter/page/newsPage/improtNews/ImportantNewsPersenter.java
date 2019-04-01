package com.release.mvp.presenter.page.newsPage.improtNews;

import com.release.mvp.http.RetrofitHelper;
import com.release.mvp.modle.ImportantNewsBean;
import com.release.mvp.ui.page.newsPage.ImportantNewsFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Mr.release
 * @create 2019/3/29
 * @Describe
 */
public class ImportantNewsPersenter {

    private ImportantNewsView mView;
    public List<ImportantNewsBean.DataBean> list = new ArrayList<>();

    public ImportantNewsPersenter(ImportantNewsView view) {
        this.mView = view;
    }

    public void loadData(ImportantNewsFragment fragment){
        if (list.size() != 0) return;

        RetrofitHelper.getImportantNewAPI()
                .getImportantNews(1,1,20)
                .compose(fragment.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ImportantNewsBean -> {
                    list.addAll(ImportantNewsBean.getData());
                    mView.finishTask();
                }, throwable -> mView.showEmptyView());
    }

}
