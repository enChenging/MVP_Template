package com.release.mvp.utils.baserx;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class RxBus {
//    private static volatile RxBus mInstance;
//    private final  Subject<Object>  subject;
    private final FlowableProcessor<Object> subject;
    private Disposable dispoable;


    public RxBus() {
//        subject = PublishSubject.create().toSerialized();
        // PublishSubject只会把在订阅发生的时间点之后,来自原始Flowable的数据发射给观察者
        subject = PublishProcessor.create().toSerialized();
    }

//    public static RxBus getInstance() {
//        if (mInstance == null) {
//            synchronized (RxBus.class) {
//                if (mInstance == null) {
//                    mInstance = new RxBus();
//                }
//            }
//        }
//        return mInstance;
//    }


    /**
     * 发送事件
     *
     * @param object
     */
    public void send(Object object) {
        subject.onNext(object);
    }


    /**
     * 返回指定类型的Observable实例
     * @param classType
     * @param <T>
     * @return
     */
//    public <T> Observable<T> toObservale(Class<T> classType) {
//        return subject.ofType(classType);
//    }
    public <T> Flowable<T> toFlowable(Class<T> classType) {
        return subject.ofType(classType);
    }


    /**
     * 订阅
     * @param classType
     * @param next
     * @param <T>
     * @return
     */
//    public <T> Disposable receive(Class<T> classType, Consumer<T> next) {
//        return  toObservale(classType)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(next);
//    }
        public <T> Disposable receive(Class<T> classType, Consumer<T> next) {
            return toFlowable(classType)
                    .compose(RxUtil.<T>rxSchedulerHelper())
                    .subscribe(next);
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
