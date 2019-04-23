package com.release.mvp.presenter.base;

/**
 * @author Mr.release
 * @create 2019/4/12
 * @Describe
 */
public interface LoadDataView<T> extends BaseView {


    void loadDataView(T data);

    void loadMoreDataView(T data);

    void loadNoDataView();
}
