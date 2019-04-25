package com.release.mvp.http.api;


import com.release.mvp.bean.RecommendPageBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author Mr.release
 * @create 2019/3/29
 * @Describe
 */
public interface RecommendServiceApi {

    @POST("it")
    Flowable<RecommendPageBean> getRecommendData(@Query("key") String key, @Query("num") int num);
}
