package com.release.mvp.http;

import com.release.mvp.App;
import com.release.mvp.BuildConfig;
import com.release.mvp.bean.NewsDetailInfoBean;
import com.release.mvp.bean.NewsInfoBean;
import com.release.mvp.bean.PhotoSetInfoBean;
import com.release.mvp.bean.RecommendPageBean;
import com.release.mvp.bean.SpecialInfoBean;
import com.release.mvp.dao.VideoInfo;
import com.release.mvp.http.api.BaseURL;
import com.release.mvp.http.api.NewsServiceApi;
import com.release.mvp.http.api.RecommendServiceApi;
import com.release.mvp.utils.CommonUtil;
import com.release.mvp.utils.LogUtils;
import com.release.mvp.utils.StringUtils;
import com.release.mvp.utils.baserx.RxUtil;

import org.reactivestreams.Publisher;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.release.mvp.ui.base.Constants.INCREASE_PAGE;

/**
 * @author Mr.release
 * @create 2019/3/29
 * @Describe
 */

public class RetrofitHelper {

    private static final String TAG = RetrofitHelper.class.getSimpleName();

    private static final String HEAD_LINE_NEWS = "T1348647909107";

    private static OkHttpClient.Builder builder;

    static {
        initOkHttpClient();
    }


    private static void initOkHttpClient() {

        if (builder == null) {
            synchronized (RetrofitHelper.class) {
                if (builder == null) {
                    HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    Cache cache = new Cache(new File(App.getInstance().getCacheDir(), "HttpCache"), 1024 * 1024 * 10);
                    builder = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(new headerIntercepteor())
                            .addNetworkInterceptor(new CacheInterceptor())
                            .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS);
                    if (BuildConfig.LOG_DEBUG) {
                        builder.addInterceptor(interceptor);
                    }
                }
            }
        }
    }

    private static <T> T createApi(String baseUrl, Class<T> cls) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(cls);
    }

    private static NewsServiceApi createNewsServiceApi() {
        return createApi(BaseURL.NEWS_HOST, NewsServiceApi.class);
    }

    private static RecommendServiceApi createRecommendServiceApi() {
        return createApi(BaseURL.RECOMMEND_HOST, RecommendServiceApi.class);
    }

    /**
     * 打印返回的json数据拦截器
     */
    private static class headerIntercepteor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request request = chain.request();
            Buffer requestBuffer = new Buffer();

            if (request == null)
                throw new RuntimeException("Request返回值不能为空");

            RequestBody requestBody = request.body();

            if (requestBody != null)
                requestBody.writeTo(requestBuffer);
            else
                LogUtils.d("LogTAG", "request.body() == null");

            //打印url信息
            LogUtils.w(request.url() + (requestBody != null ? "?" + _parseParams(requestBody, requestBuffer) : ""));
            final Response response = chain.proceed(request);

            if (response == null) {
                throw new RuntimeException("Response返回值不能为空");
            }

            return response;
        }
    }

    @NonNull
    private static String _parseParams(RequestBody body, Buffer requestBuffer) throws UnsupportedEncodingException {
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(requestBuffer.readUtf8(), "UTF-8");
        }
        return "null";
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
            // 无网络时，设置超时为1周
            int maxStale = 60 * 60 * 24 * 7;
            Request request = chain.request();
            if (CommonUtil.isNetworkAvailable(App.getInstance())) {
                //有网络时只从网络获取
                request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
            } else {
                //无网络时只从缓存中读取
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);
            if (CommonUtil.isNetworkAvailable(App.getInstance())) {
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

    /************************************ API *******************************************/


    /**
     * 获取要闻
     *
     * @param newsId
     * @param page
     * @return
     */
    public static Flowable<NewsInfoBean> getImportantNewAPI(String newsId, int page) {
        String type;
        if (newsId != null && newsId.equals(HEAD_LINE_NEWS))
            type = "headline";
        else
            type = "list";
        return createNewsServiceApi()
                .getImportantNews(type, newsId, page * INCREASE_PAGE)
                .flatMap(new Function<Map<String, List<NewsInfoBean>>, Publisher<NewsInfoBean>>() {
                    @Override
                    public Publisher<NewsInfoBean> apply(Map<String, List<NewsInfoBean>> stringListMap) throws Exception {
                        return Flowable.fromIterable(stringListMap.get(newsId));
                    }
                })
                .compose(RxUtil.rxSchedulerHelper());
    }


    /**
     * 获取新闻详情
     *
     * @param newsId
     * @return
     */
    public static Flowable<NewsDetailInfoBean> getNewsDetailAPI(String newsId) {
        return createNewsServiceApi()
                .getNewsDetail(newsId)
                .flatMap(new Function<Map<String, NewsDetailInfoBean>, Publisher<NewsDetailInfoBean>>() {
                    @Override
                    public Publisher<NewsDetailInfoBean> apply(Map<String, NewsDetailInfoBean> stringNewsDetailInfoBeanMap) throws Exception {
                        return Flowable.just(stringNewsDetailInfoBeanMap.get(newsId));
                    }
                })
                .compose(RxUtil.rxSchedulerHelper());
    }


    /**
     * 获取专题新闻
     *
     * @param specialId
     * @return
     */
    public static Flowable<SpecialInfoBean> getNewsSpecialAPI(String specialId) {
        return createNewsServiceApi()
                .getSpecial(specialId)
                .flatMap(stringSpecialInfoBeanMap -> Flowable.just(stringSpecialInfoBeanMap.get(specialId)))
                .compose(RxUtil.rxSchedulerHelper());
    }


    /**
     * 获取新闻图集
     *
     * @param photoId
     * @return
     */
    public static Flowable<PhotoSetInfoBean> getPhotoAlbumAPI(String photoId) {
        return createNewsServiceApi()
                .getPhotoAlbum(StringUtils.clipPhotoSetId(photoId))
                .compose(RxUtil.rxSchedulerHelper());
    }

    /**
     * 获取视频列表
     *
     * @param videoId
     * @param page
     * @return
     */
    public static Flowable<List<VideoInfo>> getVideoListAPI(String videoId, int page) {
        return createNewsServiceApi()
                .getVideoList(videoId, page * INCREASE_PAGE / 2)
                .flatMap(new Function<Map<String, List<VideoInfo>>, Publisher<List<VideoInfo>>>() {
                    @Override
                    public Publisher<List<VideoInfo>> apply(Map<String, List<VideoInfo>> stringListMap) throws Exception {
                        return Flowable.just(stringListMap.get(videoId));
                    }
                })
                .compose(RxUtil.rxSchedulerHelper());
    }

    public static Flowable<RecommendPageBean> getRecommendData(String key, int mum) {
        return createRecommendServiceApi()
                .getRecommendData(key, mum * INCREASE_PAGE / 2)
                .compose(RxUtil.rxSchedulerHelper());

    }
}