package com.release.mvp.http.api;


import com.release.mvp.modle.ImportantNewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Mr.release
 * @create 2019/3/29
 * @Describe
 */
public interface NewsService {

    @GET("ios/cf/dish_list.php?")
    Observable<ImportantNewsBean> getImportantNews(@Query("stage_id")int id,
                                                   @Query("page")int page,
                                                   @Query("limit")int limit);
}
