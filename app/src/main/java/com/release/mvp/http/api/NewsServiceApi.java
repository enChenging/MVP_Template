package com.release.mvp.http.api;


import com.release.mvp.bean.NewsDetailInfoBean;
import com.release.mvp.bean.NewsInfoBean;
import com.release.mvp.bean.PhotoSetInfoBean;
import com.release.mvp.bean.SpecialInfoBean;
import com.release.mvp.dao.VideoInfo;
import com.release.mvp.utils.response.BaseResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * @author Mr.release
 * @create 2019/3/29
 * @Describe
 */
public interface NewsServiceApi {

    String CACHE_CONTROL_NETWORK = "Cache-Control: public, max-age=3600";
    // 避免出现 HTTP 403 Forbidden，参考：http://stackoverflow.com/questions/13670692/403-forbidden-with-java-but-not-web-browser
    String AVOID_HTTP403_FORBIDDEN = "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";

    /**
     * 要闻
     *
     * @param type
     * @param id
     * @param startPage
     * @return
     */
    @Headers(CACHE_CONTROL_NETWORK)
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Flowable<Map<String, List<NewsInfoBean>>> getImportantNews(@Path("type") String type,
                                                               @Path("id") String id,
                                                               @Path("startPage") int startPage);

    /**
     * 新闻详情
     *
     * @param newsId
     * @return
     */
    @Headers(AVOID_HTTP403_FORBIDDEN)
    @GET("nc/article/{newsId}/full.html")
    Flowable<Map<String, NewsDetailInfoBean>> getNewsDetail(@Path("newsId") String newsId);

    /**
     * 获取专题
     *
     * @param specialIde
     * @return
     */
    @Headers(CACHE_CONTROL_NETWORK)
    @GET("nc/special/{specialId}.html")
    Flowable<Map<String, SpecialInfoBean>> getSpecial(@Path("specialId") String specialIde);


    /**
     * 获取图像集
     *
     * @param photoId
     * @return
     */
    @Headers(CACHE_CONTROL_NETWORK)
    @GET("photo/api/set/{photoId}.json")
    Flowable<PhotoSetInfoBean> getPhotoAlbum(@Path("photoId") String photoId);

    /**
     * 获取视频
     *
     * @param id
     * @param startPage
     * @return
     */
    @Headers(AVOID_HTTP403_FORBIDDEN)
    @GET("nc/video/list/{id}/n/{startPage}-10.html")
    Flowable<Map<String, List<VideoInfo>>> getVideoList(@Path("id") String id,
                                                        @Path("startPage") int startPage);
}
