package com.release.mvp.http.api;


import com.release.mvp.modle.RecommendPageBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author Mr.release
 * @create 2019/3/29
 * @Describe
 */
public interface RecommendService {

    @GET("api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getSlideshow")
    Observable<RecommendPageBean> getRecommendData();

}
