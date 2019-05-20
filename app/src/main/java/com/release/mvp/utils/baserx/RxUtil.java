package com.release.mvp.utils.baserx;

import android.text.TextUtils;

import androidx.lifecycle.LifecycleOwner;

import com.release.mvp.App;
import com.release.mvp.utils.ToastUtils;
import com.release.mvp.utils.response.BaseResponse;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/3 0003.
 */

public class RxUtil {

    private RxUtil() {
        throw new IllegalStateException("Can't instance the RxUtil");
    }

    public static <T> AutoDisposeConverter<T> bindLifecycle(LifecycleOwner lifecycleOwner) {

        return AutoDispose.autoDisposable(
                AndroidLifecycleScopeProvider.from(lifecycleOwner)
        );
    }

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> rxObservableSchedulerHelper() {

        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<BaseResponse<T>, T> handleResult() {   //compose判断结果
        return new FlowableTransformer<BaseResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<BaseResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<BaseResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(BaseResponse<T> baseResponse) {
                        if (baseResponse.isTokenExpire()) {
                            App.getInstance().tokenExpire();

                            return Flowable.error(new ApiException(baseResponse.msg));
                        } else if (baseResponse.success()) {
                            if (baseResponse.data != null) {
                                return createData(baseResponse.data);
                            } else {
                                ToastUtils.show(baseResponse.msg);
                                return null;
                            }
                        } else if (baseResponse.code == 406) {
                            App.getInstance().tokenExpire();
                            return null;
                        } else {
                            if (baseResponse == null || TextUtils.isEmpty(baseResponse.msg)) {
                                return Flowable.error(new ApiException("服务器返回error"));
                            } else {
                                return Flowable.error(new ApiException(baseResponse.msg));
                            }
                        }
                    }
                });
            }
        };
    }


    /**
     * 生成Flowable
     *
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
