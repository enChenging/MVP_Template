package com.release.mvp.ui.page.news_page;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.ViewConfiguration;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.release.mvp.R;
import com.release.mvp.bean.NewsDetailInfoBean;
import com.release.mvp.presenter.page.newsPage.detail.NewsDetailPresenter;
import com.release.mvp.presenter.page.newsPage.detail.NewsDetailView;
import com.release.mvp.ui.base.BaseActivity;
import com.release.mvp.utils.AnimateHelper;
import com.release.mvp.utils.ListUtils;
import com.release.mvp.utils.NewsUtils;
import com.release.mvp.widget.EmptyLayout;
import com.release.mvp.widget.IToolBar;
import com.release.mvp.widget.PullScrollView;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnUrlClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.release.mvp.ui.base.Constants.NEWS_ID_KEY;

/**
 * 新闻详情
 * @author Mr.release
 * @create 2019/4/15
 * @Describe
 */
public class NewsDetailActivity extends BaseActivity<NewsDetailPresenter> implements NewsDetailView {
    private static final String TAG = NewsDetailActivity.class.getSimpleName();
    @BindView(R.id.tool_bar)
    IToolBar mToolBar;
    @BindView(R.id.tv_title_content)
    TextView mTvTitleContent;
    @BindView(R.id.tv_source)
    TextView mTvSource;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.vs_related_content)
    ViewStub mVsRelatedContent;
    @BindView(R.id.tv_next_title)
    TextView mTvNextTitle;
    @BindView(R.id.ll_foot_view)
    LinearLayout mLlFootView;
    @BindView(R.id.scroll_view)
    PullScrollView mScrollView;
    @BindView(R.id.back2)
    ImageView mBack2;
    @BindView(R.id.tv_title2)
    TextView mTvTitle2;
    @BindView(R.id.tv_right2)
    TextView mTvRight2;
    @BindView(R.id.toolBar2)
    Toolbar mToolBar2;
    @BindView(R.id.ll_top_bar2)
    LinearLayout mLlTopBar2;
    @BindView(R.id.fab_coping)
    FloatingActionButton mFabCoping;


    private int mMinScrollSlop;
    private Animator mTopBarAnimator;
    private int mLastScrollY = 0;
    private String mNextNewsId;


    public static void start(Context context, String newsId) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(NEWS_ID_KEY, newsId);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.slide_right_entry, R.anim.hold);
    }

    private void startInside(String newsId) {
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra(NEWS_ID_KEY, newsId);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_bottom_entry, R.anim.hold);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news_detail;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String newsId = intent.getStringExtra(NEWS_ID_KEY);
        mPresenter.setNewsId(newsId);

        RichText.initCacheDir(this);
        RichText.debugMode = false;

        // 最小触摸滑动距离
        mMinScrollSlop = ViewConfiguration.get(this).getScaledTouchSlop();
    }


    @Override
    public void initListener() {
        int topBarHeight = getResources().getDimensionPixelSize(R.dimen.default_toolbar_height);

        mBack2.setOnClickListener((v -> finish()));

        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > topBarHeight) {
                    if (AnimateHelper.isRunning(mTopBarAnimator)) {
                        return;
                    }
                    if (Math.abs(scrollY - mLastScrollY) > mMinScrollSlop) {
                        boolean isPullUp = scrollY > mLastScrollY;
                        mLastScrollY = scrollY;
                        if (isPullUp && mLlTopBar2.getTranslationY() != -topBarHeight) {
                            mTopBarAnimator = AnimateHelper.doMoveVertical(mLlTopBar2, (int) mLlTopBar2.getTranslationY(),
                                    -topBarHeight, 300);
                        } else if (!isPullUp && mLlTopBar2.getTranslationY() != 0) {
                            mTopBarAnimator = AnimateHelper.doMoveVertical(mLlTopBar2, (int) mLlTopBar2.getTranslationY(),
                                    0, 300);
                        }
                    }
                } else {
                    if (mLlTopBar2.getTranslationY() != -topBarHeight) {
                        AnimateHelper.stopAnimator(mTopBarAnimator);
                        ViewCompat.setTranslationY(mLlTopBar2, -topBarHeight);
                        mLastScrollY = 0;
                    }
                }
            }
        });
        mScrollView.setFootView(mLlFootView);
        mScrollView.setPullListener(new PullScrollView.OnPullListener() {

            @Override
            public boolean isDoPull() {
                if (mEmptyLayout.getEmptyStatus() != EmptyLayout.STATUS_HIDE) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean handlePull() {
                if (TextUtils.isEmpty(mNextNewsId)) {
                    return false;
                } else {
                    startInside(mNextNewsId);
                    return true;
                }
            }
        });
    }

    @Override
    public void updateViews(boolean isRefresh) {
        mPresenter.loadData();
    }

    @Override
    public void loadDataView(NewsDetailInfoBean newsDetailInfoBean) {

        mTvTitleContent.setText(newsDetailInfoBean.getTitle());
        mTvSource.setText(newsDetailInfoBean.getSource());
        mTvTime.setText(newsDetailInfoBean.getPtime());

        RichText.from(newsDetailInfoBean.getBody()).into(mTvContent);
        _handleSpInfo(newsDetailInfoBean.getSpinfo());
        _handleRelatedNews(newsDetailInfoBean);

    }

    /**
     * 处理关联的内容
     *
     * @param spinfo
     */
    private void _handleSpInfo(List<NewsDetailInfoBean.SpinfoBean> spinfo) {
        if (!ListUtils.isEmpty(spinfo)) {
            ViewStub stub = findViewById(R.id.vs_related_content);
            assert stub != null;
            stub.inflate();
            TextView tvType = findViewById(R.id.tv_type);
            TextView tvRelatedContent = findViewById(R.id.tv_related_content);
            tvType.setText(spinfo.get(0).getSptype());
            RichText.from(spinfo.get(0).getSpcontent())
                    // 这里处理超链接的点击
                    .urlClick(new OnUrlClickListener() {
                        @Override
                        public boolean urlClicked(String url) {
                            String newsId = NewsUtils.clipNewsIdFromUrl(url);
                            if (newsId != null) {
                                mPresenter.setNewsId(newsId);
                                onRetry();
                            }
                            return true;
                        }
                    })
                    .into(tvRelatedContent);
        }
    }

    /**
     * 处理关联新闻
     *
     * @param newsDetailBean
     */
    private void _handleRelatedNews(NewsDetailInfoBean newsDetailBean) {
        if (ListUtils.isEmpty(newsDetailBean.getRelative_sys())) {
            mTvNextTitle.setText("没有相关文章了");
        } else {
            mNextNewsId = newsDetailBean.getRelative_sys().get(0).getId();
            mTvNextTitle.setText(newsDetailBean.getRelative_sys().get(0).getTitle());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RichText.recycle();
    }

    @OnClick(R.id.fab_coping)
    public void onViewClicked() {
        mScrollView.fullScroll(ScrollView.FOCUS_UP);
    }
}
