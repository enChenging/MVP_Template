package com.release.mvp.ui.page.news_page;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dl7.tag.TagLayout;
import com.dl7.tag.TagView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.release.mvp.R;
import com.release.mvp.bean.SpecialInfoBean;
import com.release.mvp.presenter.page.newsPage.special.NewsSpecialPresenter;
import com.release.mvp.presenter.page.newsPage.special.NewsSpecialView;
import com.release.mvp.ui.adapter.NewsSpecialAdapter;
import com.release.mvp.ui.adapter.item.SpecialItem;
import com.release.mvp.ui.base.BaseActivity;
import com.release.mvp.utils.AnimateHelper;
import com.release.mvp.utils.DefIconFactory;
import com.release.mvp.utils.ImageLoader;
import com.release.mvp.utils.baserx.RxUtil;
import com.release.mvp.widget.IToolBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import static com.release.mvp.ui.base.Constants.SPECIAL_KEY;

/**
 * 新闻专题
 *
 * @author Mr.release
 * @create 2019/4/15
 * @Describe
 */
public class NewsSpecialActivity extends BaseActivity<NewsSpecialPresenter> implements NewsSpecialView {

    private static final String TAG = NewsSpecialActivity.class.getSimpleName();

    @BindView(R.id.rv_news_list)
    RecyclerView mRvNewsList;
    @BindView(R.id.tool_bar)
    IToolBar mToolBar;
    @BindView(R.id.fab_coping)
    FloatingActionButton mFabCoping;
    @Inject
    NewsSpecialAdapter mAdapter;


    private final int[] mSkipId = new int[20];
    private TagLayout mTagLayout;
    private LinearLayoutManager mLinearLayoutManager;
    private Animator mTopBarAnimator;


    public static void start(Context context, String newsId) {
        Intent intent = new Intent(context, NewsSpecialActivity.class);
        intent.putExtra(SPECIAL_KEY, newsId);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.slide_right_entry, R.anim.hold);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_special;
    }

    @Override
    public void initView() {
        mToolBar.setBackgroundColor(getResources().getColor(R.color.white));
        String specialId = getIntent().getStringExtra(SPECIAL_KEY);
        mPresenter.setSpeicalId(specialId);

        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRvNewsList.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRvNewsList.setLayoutManager(mLinearLayoutManager);
        mRvNewsList.setAdapter(mAdapter);

    }

    boolean change;

    @Override
    public void initListener() {

        int topBarHeight = getResources().getDimensionPixelSize(R.dimen.default_toolbar_height);

        mTopBarAnimator = AnimateHelper.doMoveVertical(mToolBar, (int) mToolBar.getTranslationY(), -topBarHeight, 0);

        mRvNewsList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy < 0 && !change) {
                    if (AnimateHelper.isRunning(mTopBarAnimator))
                        return;
                    mTopBarAnimator = AnimateHelper.doMoveVertical(mToolBar, (int) mToolBar.getTranslationY(), 0, 300);
                    change = true;
                } else if (dy > 0 && change) {
                    AnimateHelper.stopAnimator(mTopBarAnimator);
                    ViewCompat.setTranslationY(mToolBar, -topBarHeight);
                    change = false;
                }
            }
        });
    }

    @Override
    public void updateViews(boolean isRefresh) {
        mPresenter.loadData();
    }

    @Override
    public void loadDataView(List<SpecialItem> specialItems) {
        mAdapter.setNewData(specialItems);
        _handleTagLayout(specialItems);
    }

    @Override
    public void loadHeadView(SpecialInfoBean specialBean) {
        View headView = LayoutInflater.from(this).inflate(R.layout.head_special, null);
        ImageView mIvBanner = headView.findViewById(R.id.iv_banner);

        ImageLoader.loadFitCenter(this, specialBean.getBanner(), mIvBanner, DefIconFactory.provideIcon());

        // 添加导语
        if (!TextUtils.isEmpty(specialBean.getDigest())) {
            ViewStub stub = headView.findViewById(R.id.vs_digest);
            assert stub != null;
            stub.inflate();
            TextView tvDigest = headView.findViewById(R.id.tv_digest);
            tvDigest.setText(specialBean.getDigest());
        }
        mTagLayout = headView.findViewById(R.id.tag_layout);
        mAdapter.addHeaderView(headView);
    }

    /**
     * 处理导航标签
     *
     * @param specialItems
     */
    @SuppressLint("CheckResult")
    private void _handleTagLayout(List<SpecialItem> specialItems) {
        Observable.fromIterable(specialItems)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<SpecialItem>() {
                    int i = 0;
                    int index = mAdapter.getHeaderViewsCount();  // 存在一个 HeadView 所以从1算起

                    @Override
                    public boolean test(SpecialItem specialItem) throws Exception {

                        if (specialItem.isHeader) {
                            // 记录头部的列表索引值，用来跳转
                            mSkipId[i++] = index;
                        }
                        index++;
                        return specialItem.isHeader;
                    }
                })
                .map(new Function<SpecialItem, String>() {
                    @Override
                    public String apply(SpecialItem specialItem) throws Exception {
                        return _clipHeadStr(specialItem.header);
                    }
                })
                .as(RxUtil.bindLifecycle(this))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mTagLayout.addTag(s);
                    }
                });

        mTagLayout.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @TagView.TagMode int tagMode) {
                // 跳转到对应position,比scrollToPosition（）精确
                mLinearLayoutManager.scrollToPositionWithOffset(mSkipId[position], 0);
            }
        });
    }

    private String _clipHeadStr(String headStr) {
        String head = null;
        int index = headStr.indexOf(" ");
        if (index != -1) {
            head = headStr.substring(index, headStr.length());
        }
        return head;
    }

    @OnClick(R.id.fab_coping)
    public void onViewClicked() {
        mLinearLayoutManager.scrollToPosition(0);
    }

}
