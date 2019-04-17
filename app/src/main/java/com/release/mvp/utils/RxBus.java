package com.release.mvp.utils;

import com.trello.rxlifecycle3.components.RxFragment;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class RxBus {
    private static volatile RxBus mInstance;
    private final Subject<Object> subject = PublishSubject.create().toSerialized();
    private Disposable dispoable;


    private RxBus() {
    }

    public static RxBus getInstance() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }


    /**
     * 发送事件
     *
     * @param object
     */
    public void send(Object object) {
        subject.onNext(object);
    }


    /**
     * @param classType
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservale(Class<T> classType) {
        return subject.ofType(classType);
    }


    /**
     * 订阅
     *
     * @param bean
     * @param consumer
     */
    public void subscribe(Class bean, Consumer consumer) {
        dispoable = toObservale(bean).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer);
    }

    public void subscribe(Class bean, Consumer consumer, RxAppCompatActivity context) {
        dispoable = toObservale(bean).compose(context.<Long>bindToLifecycle()).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer);
    }

    public void subscribe(Class bean, Consumer consumer, RxFragment context) {
        dispoable = toObservale(bean).compose(context.<Long>bindToLifecycle()).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer);
    }

    /**
     * 取消订阅
     */
    public void unSubcribe() {
        if (dispoable != null && dispoable.isDisposed()) {
            dispoable.dispose();
        }
    }
}
