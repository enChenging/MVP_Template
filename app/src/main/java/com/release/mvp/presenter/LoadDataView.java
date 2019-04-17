package com.release.mvp.presenter;

/**
 * @author Mr.release
 * @create 2019/4/12
 * @Describe
 */
public interface LoadDataView<T> extends BaseView {


    void loadData(T data);

    void loadMoreData(T data);

    void loadNoData();
}
