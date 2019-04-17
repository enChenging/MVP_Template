package com.release.mvp.ui.page.news_page;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dl7.drag.DragSlopLayout;
import com.release.mvp.R;
import com.release.mvp.bean.PhotoSetInfoBean;
import com.release.mvp.presenter.page.newsPage.photo_album.PhotoAlbumPresenter;
import com.release.mvp.presenter.page.newsPage.photo_album.PhotoAlbumView;
import com.release.mvp.ui.adapter.PhotoSetAdapter;
import com.release.mvp.ui.base.BaseActivity;
import com.release.mvp.utils.StatusBarUtil;
import com.release.mvp.widget.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.release.mvp.ui.base.Constants.PHOTO_SET_KEY;

/**
 * @author Mr.release
 * @create 2019/4/16
 * @Describe
 */
public class PhotoAlbumActivity extends BaseActivity implements PhotoAlbumView {


    @BindView(R.id.vp_photo)
    PhotoViewPager mVpPhoto;
    @BindView(R.id.tv_title2)
    TextView mTvTitle2;
    @BindView(R.id.tv_index)
    TextView mTvIndex;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.drag_layout)
    DragSlopLayout mDragLayout;
    @BindView(R.id.ll_tool_bar)
    LinearLayout mLlToolBar;
    @BindView(R.id.ll_bottom_content)
    LinearLayout mLlBottomContent;

    private PhotoAlbumPresenter mPresenter;
    private String mPhotoSetId;
    private PhotoSetAdapter mAdapter;
    private boolean mIsHideToolbar = false;
    private List<PhotoSetInfoBean.PhotosBean> mPhotos;

    public static void start(Context context, String newsId) {
        Intent intent = new Intent(context, PhotoAlbumActivity.class);
        intent.putExtra(PHOTO_SET_KEY, newsId);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.slide_right_entry, R.anim.hold);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_photo_album;
    }

    @Override
    public void initView() {

        mPhotoSetId = getIntent().getStringExtra(PHOTO_SET_KEY);

        mPresenter = new PhotoAlbumPresenter(this, mPhotoSetId);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void updateViews(boolean isRefresh) {
        mPresenter.loadData(isRefresh);
    }

    @Override
    public void loadPhotoData(PhotoSetInfoBean photoSetBean) {
        List<String> imgUrls = new ArrayList<>();
        mPhotos = photoSetBean.getPhotos();
        for (PhotoSetInfoBean.PhotosBean photo : mPhotos) {
            imgUrls.add(photo.getImgurl());
        }

        mAdapter = new PhotoSetAdapter(this, imgUrls);
        mVpPhoto.setAdapter(mAdapter);
        mVpPhoto.setOffscreenPageLimit(imgUrls.size());

        mTvCount.setText(mPhotos.size() + "");
        mTvTitle.setText(photoSetBean.getSetname());
        mTvIndex.setText(1 + "/");
        mTvContent.setText(mPhotos.get(0).getNote());

        mVpPhoto.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTvContent.setText(mPhotos.get(position).getNote());
                mTvIndex.setText((position + 1) + "/");
            }
        });
        mAdapter.setTapListener(new PhotoSetAdapter.OnTapListener() {
            @Override
            public void onPhotoClick() {
                mIsHideToolbar = !mIsHideToolbar;
                if (mIsHideToolbar) {
                    mDragLayout.scrollOutScreen(300);
                    mLlToolBar.animate().translationY(-mLlToolBar.getBottom()).setDuration(300);
                } else {
                    mDragLayout.scrollInScreen(300);
                    mLlToolBar.animate().translationY(0).setDuration(300);
                }
            }
        });
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.black));
    }
}
