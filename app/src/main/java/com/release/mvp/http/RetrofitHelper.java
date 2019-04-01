package com.release.mvp.http;

import com.release.mvp.http.api.BaseURL;
import com.release.mvp.http.api.NewsService;
import com.release.mvp.http.api.RecommendService;
import com.release.mvp.ui.base.BaseApplication;
import com.release.mvp.utils.CommonUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Mr.release
 * @create 2019/3/29
 * @Describe
 */

public class RetrofitHelper {
    private static OkHttpClient mOkHttpClient;

    static {
        initOkHttpClient();
    }

    public static NewsService getImportantNewAPI() {
        return createApi(BaseURL.COOK_BOOK_BASE_URL, NewsService.class);
    }

    public static RecommendService getRecommendAPI() {
        return createApi(BaseURL.TEA_BASE_URL, RecommendService.class);
    }


    private static void initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (mOkHttpClient == null) {

                    Cache cache = new Cache(new File(BaseApplication.mContext.getCacheDir(), "HttpCache"), 1024 * 1024 * 10);
                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
//                            .addInterceptor(interceptor)
//                            .addInterceptor(new UserAgentInterceptor())
                            .addNetworkInterceptor(new CacheInterceptor())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    private static <T> T createApi(String baseUrl,Class<T> cls){
        Retrofit retrofit=new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(cls);
    }


    private static class UserAgentInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request requestWithUserAgent = originalRequest.newBuilder()
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", BaseURL.COMMON_UA_STR)
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }


    private static class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            // 有网络时 设置缓存超时时间1个小时
            int maxAge = 60 * 60;
            // 无网络时，设置超时为1天
            int maxStale = 60 * 60 * 24;
            Request request = chain.request();
            if (CommonUtil.isNetworkAvailable(BaseApplication.mContext)) {
                //有网络时只从网络获取
                request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
            } else {
                //无网络时只从缓存中读取
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);
            if (CommonUtil.isNetworkAvailable(BaseApplication.mContext)) {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    }
}