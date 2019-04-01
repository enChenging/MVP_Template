package com.release.mvp.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class RxHelper {

    private RxHelper() {
        throw new AssertionError();
    }

    /**
     * 倒计时
     *
     * @param time
     * @return
     */
    public static Observable<Long> countdown(int time) {

        if (time < 0) time = 0;
        final int countTime = time;
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@io.reactivex.annotations.NonNull Long aLong) throws Exception {
                        return countTime - aLong;
                    }
                })
                .take(countTime + 1);
    }
}
