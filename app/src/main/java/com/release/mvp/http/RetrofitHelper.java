package com.release.mvp.http;

import com.release.mvp.bean.NewsDetailInfoBean;
import com.release.mvp.bean.NewsInfoBean;
import com.release.mvp.bean.PhotoSetInfoBean;
import com.release.mvp.bean.SpecialInfoBean;
import com.release.mvp.dao.VideoInfo;
import com.release.mvp.http.api.BaseURL;
import com.release.mvp.http.api.NewsService;
import com.release.mvp.http.api.RecommendService;
import com.release.mvp.ui.base.BaseApplication;
import com.release.mvp.utils.CommonUtil;
import com.release.mvp.utils.LogUtils;
import com.release.mvp.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
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

    private static OkHttpClient mOkHttpClient;

    static {
        initOkHttpClient();
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
                            .addInterceptor(new LoggingInterceptor())
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

    private static <T> T createApi(String baseUrl, Class<T> cls) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(cls);
    }


    @NonNull
    private static String _parseParams(RequestBody body, Buffer requestBuffer) throws UnsupportedEncodingException {
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(requestBuffer.readUtf8(), "UTF-8");
        }
        return "null";
    }

    /**
     * 打印返回的json数据拦截器
     */
    private static class LoggingInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request request = chain.request();
            Buffer requestBuffer = new Buffer();

            if (request == null) {
                throw new RuntimeException("Request返回值不能为空");
            }

            if (request.body() != null) {
                request.body().writeTo(requestBuffer);
            } else {
                LogUtils.d("LogTAG", "request.body() == null");
            }
            //打印url信息
            LogUtils.w(request.url() + (request.body() != null ? "?" + _parseParams(request.body(), requestBuffer) : ""));
            final Response response = chain.proceed(request);

            if (response == null) {
                throw new RuntimeException("Response返回值不能为空");
            }

            return response;
        }
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

    /************************************ API *******************************************/


    /**
     * 获取要闻
     *
     * @param newsId
     * @param page
     * @return
     */
    public static Observable<NewsInfoBean> getImportantNewAPI(String newsId, int page) {
        String type;
        if (newsId.equals(HEAD_LINE_NEWS)) {
            type = "headline";
        } else {
            type = "list";
        }
        return createApi(BaseURL.NEWS_HOST, NewsService.class)
                .getImportantNews(type, newsId, page * INCREASE_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Map<String, List<NewsInfoBean>>, ObservableSource<NewsInfoBean>>() {
                    @Override
                    public ObservableSource<NewsInfoBean> apply(Map<String, List<NewsInfoBean>> stringListMap) throws Exception {
                        Observable<NewsInfoBean> observable = Observable.fromIterable(stringListMap.get(newsId));
                        return observable;
                    }
                });
    }


    /**
     * 获取新闻详情
     *
     * @param newsId
     * @return
     */
    public static Observable<NewsDetailInfoBean> getNewsDetailAPI(String newsId) {
        return createApi(BaseURL.NEWS_HOST, NewsService.class)
                .getNewsDetail(newsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Map<String, NewsDetailInfoBean>, ObservableSource<NewsDetailInfoBean>>() {
                    @Override
                    public ObservableSource<NewsDetailInfoBean> apply(Map<String, NewsDetailInfoBean> stringNewsDetailInfoBeanMap) throws Exception {
                        Observable<NewsDetailInfoBean> observable = Observable.just(stringNewsDetailInfoBeanMap.get(newsId));
                        return observable;
                    }
                });
    }


    /**
     * 获取专题新闻
     * @param specialId
     * @return
     */
    public static Observable<SpecialInfoBean> getNewsSpecialAPI(String specialId) {
        return createApi(BaseURL.NEWS_HOST, NewsService.class)
                .getSpecial(specialId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(stringSpecialInfoBeanMap -> Observable.just(stringSpecialInfoBeanMap.get(specialId)));
    }


    /**
     * 获取新闻图集
     * @param photoId
     * @return
     */
    public static Observable<PhotoSetInfoBean> getPhotoAlbumAPI(String photoId) {
        return createApi(BaseURL.NEWS_HOST, NewsService.class)
                .getPhotoAlbum(StringUtils.clipPhotoSetId(photoId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取视频列表
     * @param videoId
     * @param page
     * @return
     */
    public static Observable<List<VideoInfo>> getVideoListAPI(String videoId, int page) {
        return createApi(BaseURL.NEWS_HOST, NewsService.class)
                .getVideoList(videoId, page * INCREASE_PAGE / 2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Map<String, List<VideoInfo>>, ObservableSource<List<VideoInfo>>>() {
                    @Override
                    public ObservableSource<List<VideoInfo>> apply(Map<String, List<VideoInfo>> stringListMap) throws Exception {
                        return Observable.just(stringListMap.get(videoId));
                    }
                });
    }



    public static NewsService getFoodAPI() {
        return createApi(BaseURL.COOK_BOOK_BASE_URL, NewsService.class);
    }

    public static RecommendService getRecommendAPI() {
        return createApi(BaseURL.TEA_BASE_URL, RecommendService.class);
    }
}