package com.release.mvp.utils.baserx;

import com.release.mvp.App;
import com.release.mvp.utils.response.BaseResponse;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * 订阅封装
 * Created by Circle on 2017/4/3 0003.
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {

    protected CommonSubscriber() {
    }

    @Override
    public void onNext(T t) {
        try {
            if (t instanceof BaseResponse) {
                if (((BaseResponse) t).isTokenExpire()) {
                    App.getInstance().tokenExpire();
                }
            }
            _onNext(t);
        } catch (Exception e) {
            onError(e);
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        _onError(e.toString());
    }


    @Override
    public void onComplete() {
        _onComplete();
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

    protected abstract void _onComplete();

}
