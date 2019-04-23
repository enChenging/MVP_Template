package com.release.mvp.presenter.base;

/**
 * @author Mr.release
 * @create 2019/4/19
 * @Describe
 */
public abstract class BasePresenter<T extends BaseView> implements Presenter{

    protected final T view;

    protected BasePresenter(T view) {
        this.view = view;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void loadData(boolean isRefresh) {

    }

    @Override
    public void loadMoreData() {

    }
}
