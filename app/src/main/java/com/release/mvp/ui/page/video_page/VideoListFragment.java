package com.release.mvp.ui.page.video_page;

import android.os.Bundle;
import android.view.View;

import com.release.mvp.R;
import com.release.mvp.dao.VideoInfo;
import com.release.mvp.presenter.page.videoPage.video_list.VideoListPrsenter;
import com.release.mvp.presenter.page.videoPage.video_list.VideoListView;
import com.release.mvp.ui.adapter.VideoListAdapter;
import com.release.mvp.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import cn.jzvd.Jzvd;

import static com.release.mvp.ui.base.Constants.VIDEO_ID_KEY;

/**
 * @author Mr.release
 * @create 2019/4/16
 * @Describe
 */
public class VideoListFragment extends BaseFragment<VideoListPrsenter> implements VideoListView {

    private static final String TAG = VideoListFragment.class.getSimpleName();
    @BindView(R.id.rv_photo_list)
    RecyclerView mRvPhotoList;
    @Inject
    VideoListAdapter mAdapter;
    public String mVideoId;

    public static VideoListFragment newInstance(String videoId) {
        VideoListFragment fragment = new VideoListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(VIDEO_ID_KEY, videoId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video_list;
    }

    @Override
    public void initView(View view) {

        mVideoId = getArguments().getString(VIDEO_ID_KEY);
//        mAdapter = new VideoListAdapter(R.layout.adapter_video_list, null);
        mRvPhotoList.setHasFixedSize(true);
        mRvPhotoList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvPhotoList.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        mRvPhotoList.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                Jzvd jzvd = view.findViewById(R.id.videoplayer);
                if (jzvd != null && Jzvd.CURRENT_JZVD != null &&
                        jzvd.jzDataSource.containsTheUrl(Jzvd.CURRENT_JZVD.jzDataSource.getCurrentUrl())) {
                    if (Jzvd.CURRENT_JZVD != null && Jzvd.CURRENT_JZVD.currentScreen != Jzvd.SCREEN_WINDOW_FULLSCREEN) {
                        Jzvd.resetAllVideos();
                    }
                }
            }
        });
    }

    @Override
    public void updateViews(boolean isRefresh) {
        mPresenter.loadData(isRefresh);
    }

    @Override
    public void loadDataView(List<VideoInfo> data) {
//        LogUtils.i(TAG, "loadData: "+s);
        mAdapter.setNewData(data);

    }

    @Override
    public void loadMoreDataView(List<VideoInfo> data) {
        mAdapter.loadMoreComplete();
        mAdapter.addData(data);
    }

    @Override
    public void loadNoDataView() {
        mAdapter.loadMoreEnd();
    }
}
